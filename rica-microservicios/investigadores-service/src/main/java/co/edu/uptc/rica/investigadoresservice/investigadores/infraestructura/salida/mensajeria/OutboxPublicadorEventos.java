package co.edu.uptc.rica.investigadoresservice.investigadores.infraestructura.salida.mensajeria;

import java.time.Instant;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uptc.rica.investigadoresservice.investigadores.aplicacion.PublicadorEventos;

@Component 
public class OutboxPublicadorEventos implements PublicadorEventos {

    private final OutboxEventRepository repositorio;
    public final ObjectMapper objectMapper;

    public OutboxPublicadorEventos(OutboxEventRepository repositorio, ObjectMapper objectMapper) {
        this.repositorio = repositorio;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publicar(String topico, String clave, Map<String, Object> contenido) {
        try {
            String payload = objectMapper.writeValueAsString(contenido);
            OutboxEvent evento = new OutboxEvent(topico, clave, payload, Instant.now(), false);
            repositorio.save(evento);
        } catch (Exception e) {
            throw new RuntimeException("Error al serializar el contenido del evento", e);
        }
    }

}
