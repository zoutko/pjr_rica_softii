package co.edu.uptc.rica.ricaapi.investigadores;

import java.time.Instant;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class InvestigadorFactory {

    private final InvestigadorRepository investigadorRepository;
    private final ApplicationEventPublisher eventPublisher;

    public InvestigadorFactory(
            InvestigadorRepository investigadorRepository,
            ApplicationEventPublisher eventPublisher) {

        this.investigadorRepository = investigadorRepository;
        this.eventPublisher = eventPublisher;
    }

    public Investigador crear(String nombreCompleto,
            String correoInstitucional,
            String grupoInvestigacion) {

        CorreoInstitucional correo = new CorreoInstitucional(correoInstitucional);

        if (investigadorRepository.existsByCorreoInstitucional(correo)) {
            throw new CorreoDuplicadoException(
                    "Ya existe un investigador registrado con el correo " + correo.valor());
        }

        Investigador investigador = new Investigador(null, nombreCompleto, correo, grupoInvestigacion);

        eventPublisher.publishEvent(
                new InvestigadorRegistrado(correo.valor(), Instant.now()));

        return investigador;
    }

}
