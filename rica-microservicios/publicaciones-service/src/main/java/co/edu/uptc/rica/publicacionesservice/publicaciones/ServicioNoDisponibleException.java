package co.edu.uptc.rica.publicacionesservice.publicaciones;

public class ServicioNoDisponibleException extends RuntimeException {

    public ServicioNoDisponibleException(String message, Throwable causa) {
        super(message, causa);
    }

}
