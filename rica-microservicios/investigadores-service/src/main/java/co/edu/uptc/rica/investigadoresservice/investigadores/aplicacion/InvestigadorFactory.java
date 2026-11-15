package co.edu.uptc.rica.investigadoresservice.investigadores.aplicacion;

import java.time.Instant;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import co.edu.uptc.rica.investigadoresservice.investigadores.InvestigadorRegistrado;
import co.edu.uptc.rica.investigadoresservice.investigadores.dominio.CorreoDuplicadoException;
import co.edu.uptc.rica.investigadoresservice.investigadores.dominio.CorreoInstitucional;
import co.edu.uptc.rica.investigadoresservice.investigadores.dominio.Investigador;

@Component
public class InvestigadorFactory {

    private final RepositorioInvestigadores investigadorRepository;
    private final ApplicationEventPublisher eventPublisher;

    public InvestigadorFactory(
            RepositorioInvestigadores investigadorRepository,
            ApplicationEventPublisher eventPublisher) {

        this.investigadorRepository = investigadorRepository;
        this.eventPublisher = eventPublisher;
    }

    public Investigador crear(String nombreCompleto,
            String correoInstitucional,
            String grupoInvestigacion) {

        CorreoInstitucional correo = new CorreoInstitucional(correoInstitucional);

        if (investigadorRepository.existeCorreo(correo.valor())) {
            throw new CorreoDuplicadoException(
                    "Ya existe un investigador registrado con el correo " + correo.valor());
        }

        Investigador investigador = new Investigador(null, nombreCompleto, correo, grupoInvestigacion);

        eventPublisher.publishEvent(
                new InvestigadorRegistrado(correo.valor(), Instant.now()));

        return investigador;
    }

}
