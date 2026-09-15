package co.edu.uptc.rica.ricaapi.publicaciones;

import org.springframework.stereotype.Service;

import co.edu.uptc.rica.ricaapi.investigadores.CorreoInstitucional;


@Service 
public class LimitePublicacionesAnualesService {

    private static final int MAXIMO_POR_ANIO = 5;

    private final PublicacionRepository publicacionRepository;

    public LimitePublicacionesAnualesService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    public boolean puedeRegistrar(CorreoInstitucional correo, Publicacion nueva) {
        long registradasEsteAnio = publicacionRepository.countByInvestigadorCorreoAndAnio(correo.valor(), nueva.getAnio());
        return registradasEsteAnio < MAXIMO_POR_ANIO;
    }

}
