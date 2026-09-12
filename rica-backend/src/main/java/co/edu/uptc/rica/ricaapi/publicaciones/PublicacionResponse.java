package co.edu.uptc.rica.ricaapi.publicaciones;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
public class PublicacionResponse {
      private String investigadorCorreo,
            titulo,
            tipo;
    private Integer anio;
    private Map<String, String> detalles;

    public PublicacionResponse() {
    }


}
