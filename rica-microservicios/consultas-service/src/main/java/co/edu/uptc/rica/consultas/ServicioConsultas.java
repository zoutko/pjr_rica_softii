package co.edu.uptc.rica.consultas;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.edu.uptc.rica.compartido.RecursoNoEncontradoException;

import java.util.List;

/** Lado de consulta: solo lee, nunca modifica nada. */
@Service
public class ServicioConsultas {

    private final FichaRepository fichaRepository;

    public ServicioConsultas(FichaRepository fichaRepository) {
        this.fichaRepository = fichaRepository;
    }

    public List<FichaResponse> listar(String estado) {
        List<FichaInvestigador> fichas = (estado == null || estado.isBlank())
                ? fichaRepository.findAll()
                : fichaRepository.findByEstado(estado.toUpperCase());
        return fichas.stream().map(FichaResponse::desde).toList();
    }

    public FichaResponse buscarPorCorreo(String correo) throws Exception {
        return fichaRepository.findById(correo)
                .map(FichaResponse::desde)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No hay ficha para el investigador " + correo));
    }

    public List<FichaResponse> ranking(int top) {
        return fichaRepository.findAllByOrderByTotalPublicacionesDesc(PageRequest.of(0, Math.max(1, top)))
                .stream().map(FichaResponse::desde).toList();
    }

}
