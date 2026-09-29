package co.edu.uptc.rica.investigadoresservice.investigadores.aplicacion;

import org.springframework.stereotype.Service;

import co.edu.uptc.rica.investigadoresservice.investigadores.dominio.Investigador;
import co.edu.uptc.rica.investigadoresservice.compartido.RecursoNoEncontradoException;

import java.util.List;

@Service
public class InvestigadorService implements InvestigadorUseCase{

    private final InvestigadorFactory investigadorFactory;
    private final RepositorioInvestigadores investigadorRepository;

    public InvestigadorService(InvestigadorFactory investigadorFactory, RepositorioInvestigadores investigadorRepository) {
        this.investigadorFactory = investigadorFactory;
        this.investigadorRepository = investigadorRepository;
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
public Investigador registrar(String nombreCompleto, String correoInstitucional, String grupoInvestigacion) {
  Investigador investigador = investigadorFactory.crear(nombreCompleto, correoInstitucional, grupoInvestigacion);
  return investigadorRepository.guardar(investigador);
}

}
