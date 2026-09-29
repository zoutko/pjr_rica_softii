package co.edu.uptc.rica.ricaapi.investigadores;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerInvestigadorRegistrado {

    private static final Logger log = LoggerFactory.getLogger(ListenerInvestigadorRegistrado.class);

    @EventListener
    public void onInvestigadorRegistrado(InvestigadorRegistrado evento) {
        log.info("Investigador registrado: {} en {}", evento.correoInstitucional(), evento.ocurridoEn());
    }
}