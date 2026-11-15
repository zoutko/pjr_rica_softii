package co.edu.uptc.rica.investigadoresservice.investigadores.infraestructura.salida.mensajeria;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class Jackson3Configuration {

    @Bean
    public ObjectMapper jackson3ObjectMapper() {
        return new ObjectMapper();
    }
}
