package co.edu.uptc.rica.investigadoresservice.investigadores.infraestructura.entrada.mensajeria;

import co.edu.uptc.rica.investigadoresservice.investigadores.aplicacion.InvestigadorUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PublicacionesMarcadasListener {

    private static final Logger log = LoggerFactory.getLogger(PublicacionesMarcadasListener.class);

    private final InvestigadorUseCase investigadorService;
    private final ObjectMapper objectMapper;

    public PublicacionesMarcadasListener(InvestigadorUseCase investigadorService, ObjectMapper objectMapper) {
        this.investigadorService = investigadorService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "publicaciones.marcadas-huerfanas")
    public void alMarcarsePublicaciones(String mensaje) {
        String correo;
        try {
            correo = objectMapper.readTree(mensaje).get("correoInstitucional").asText();
            log.info("Saga: publicaciones confirmó para {}. Eliminando investigador.", correo);
            investigadorService.confirmarEliminacion(correo);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
