package co.edu.uptc.rica.investigadoresservice.investigadores.aplicacion;

import org.springframework.stereotype.Service;

import co.edu.uptc.rica.investigadoresservice.investigadores.dominio.Investigador;
import co.edu.uptc.rica.investigadoresservice.compartido.RecursoNoEncontradoException;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import java.util.List;

@Service
public class InvestigadorService implements InvestigadorUseCase {

    private final InvestigadorFactory investigadorFactory;
    private final RepositorioInvestigadores investigadorRepository;
    private final PublicadorEventos publicadorEventos;

    public InvestigadorService(InvestigadorFactory investigadorFactory,
            RepositorioInvestigadores investigadorRepository, PublicadorEventos publicadorEventos) {
        this.investigadorFactory = investigadorFactory;
        this.investigadorRepository = investigadorRepository;
        this.publicadorEventos = publicadorEventos;
    }

    @Override
    public List<Investigador> listarTodos() {
        return investigadorRepository.listarTodos();
    }

    @Override
    public Investigador buscarPorId(Long id) {
        return investigadorRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un investigador con id " + id));
    }

    @Override
    @Transactional
    public Investigador registrar(String nombreCompleto, String correoInstitucional, String grupoInvestigacion) {
        Investigador investigador = investigadorFactory.crear(nombreCompleto, correoInstitucional, grupoInvestigacion);
        Investigador guardado = investigadorRepository.guardar(investigador);

        // Transactional Outbox: el evento se guarda en la MISMA transacción que el
        // investigador.
        publicadorEventos.publicar(
                "investigadores.registrados",
                correoInstitucional,
                Map.of("id", guardado.getId(),
                        "correoInstitucional", correoInstitucional,
                        "nombreCompleto", nombreCompleto));
        return guardado;
    }

}
