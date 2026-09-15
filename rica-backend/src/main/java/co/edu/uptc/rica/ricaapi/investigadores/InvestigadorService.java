package co.edu.uptc.rica.ricaapi.investigadores;

import org.springframework.stereotype.Service;

import co.edu.uptc.rica.ricaapi.compartido.RecursoNoEncontradoException;

import java.util.List;

@Service
public class InvestigadorService {

    private final InvestigadorFactory investigadorFactory;
    private final InvestigadorRepository investigadorRepository;

    public InvestigadorService(InvestigadorFactory investigadorFactory, InvestigadorRepository investigadorRepository) {
        this.investigadorFactory = investigadorFactory;
        this.investigadorRepository = investigadorRepository;
    }

    public List<Investigador> listarTodos() {
        return investigadorRepository.findAll();
    }

    public Investigador buscarPorId(Long id) {
        return investigadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un investigador con id " + id));
    }

public Investigador registrar(String nombreCompleto, String correoInstitucional, String grupoInvestigacion) {
  Investigador investigador = investigadorFactory.crear(nombreCompleto, correoInstitucional, grupoInvestigacion);
  return investigadorRepository.save(investigador);
}

}
