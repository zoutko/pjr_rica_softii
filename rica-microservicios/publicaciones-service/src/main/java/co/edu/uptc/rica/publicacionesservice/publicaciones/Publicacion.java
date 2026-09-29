package co.edu.uptc.rica.publicacionesservice.publicaciones;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Document(collection = "publicaciones") 
public class Publicacion {

    @Id 
    private String id;

    private String investigadorCorreo,
            titulo,
            tipo;
    private Integer anio;
    private Map<String, String> detalles;

    public Publicacion() {
    }


}
