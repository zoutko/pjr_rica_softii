package co.edu.uptc.rica.publicacionesservice.publicaciones;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import co.edu.uptc.rica.publicacionesservice.compartido.RecursoNoEncontradoException;

import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final VerificadorInvestigador verificadorInvestigador;
    private final LimitePublicacionesAnualesService limitePublicacionesAnualesService;

    public PublicacionService(PublicacionRepository publicacionRepository,
            VerificadorInvestigador verificadorInvestigador,
            LimitePublicacionesAnualesService limitePublicacionesAnualesService) {
        this.publicacionRepository = publicacionRepository;
        this.verificadorInvestigador = verificadorInvestigador;
        this.limitePublicacionesAnualesService = limitePublicacionesAnualesService;
    }

    public Publicacion registrar(Publicacion publicacion) {
        if (!verificadorInvestigador.existe(publicacion.getInvestigadorCorreo())) {
            throw new RecursoNoEncontradoException(
                    "No existe un investigador con correo " + publicacion.getInvestigadorCorreo());
        } else if (!limitePublicacionesAnualesService.puedeRegistrar(
                publicacion.getInvestigadorCorreo(),
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