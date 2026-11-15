package co.edu.uptc.rica.consultas;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FichaRepository extends MongoRepository<FichaInvestigador, String> {

    List<FichaInvestigador> findByEstado(String estado);

    List<FichaInvestigador> findAllByOrderByTotalPublicacionesDesc(Pageable pageable);

}
