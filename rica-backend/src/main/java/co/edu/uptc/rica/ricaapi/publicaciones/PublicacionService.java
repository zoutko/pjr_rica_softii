package co.edu.uptc.rica.ricaapi.publicaciones;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import co.edu.uptc.rica.ricaapi.compartido.RecursoNoEncontradoException;
import co.edu.uptc.rica.ricaapi.investigadores.CorreoInstitucional;
import co.edu.uptc.rica.ricaapi.investigadores.InvestigadorRepository;

import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final InvestigadorRepository investigadorRepository;
    private final LimitePublicacionesAnualesService limitePublicacionesAnualesService;

    public PublicacionService(PublicacionRepository publicacionRepository,
                               InvestigadorRepository investigadorRepository,
                               LimitePublicacionesAnualesService limitePublicacionesAnualesService) {
        this.publicacionRepository = publicacionRepository;
        this.investigadorRepository = investigadorRepository;
        this.limitePublicacionesAnualesService = limitePublicacionesAnualesService;
    }

    public Publicacion registrar(Publicacion publicacion) {
        if (!investigadorRepository.existsByCorreoInstitucional( new CorreoInstitucional(publicacion.getInvestigadorCorreo()))) {
            throw new RecursoNoEncontradoException(
                    "No existe un investigador con correo " + publicacion.getInvestigadorCorreo());
        } else if (limitePublicacionesAnualesService.puedeRegistrar(
                new CorreoInstitucional(publicacion.getInvestigadorCorreo()),
                publicacion)) {
            throw new IllegalArgumentException(
                    "El investigador con correo " + publicacion.getInvestigadorCorreo() +
                            " ya ha alcanzado el límite de publicaciones para el año " + publicacion.getAnio());
        }
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return publicacionRepository.findByInvestigadorCorreo(investigadorCorreo);
    }

    public Publicacion buscarPorId(@NonNull String id) {
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una publicación con id " + id));
    }

}