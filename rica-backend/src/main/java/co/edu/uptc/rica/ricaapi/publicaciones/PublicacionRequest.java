package co.edu.uptc.rica.ricaapi.publicaciones;

import java.util.Map;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
public class PublicacionRequest {

    @NotBlank (message = "El correo del investigador es obligatorio")
    @Email (message = "El correo del investigador debe tener un formato válido")
    private String investigadorCorreo;
    @NotBlank (message = "El título de la publicación es obligatorio")
    private String titulo;
    @NotBlank (message = "El tipo de publicación es obligatorio")
    private String tipo;
    @NotNull (message = "El año de la publicación es obligatorio")
    private Integer anio;
    private Map<String, String> detalles;

}
