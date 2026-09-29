package co.edu.uptc.rica.investigadoresservice.investigadores.infraestructura.salida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uptc.rica.investigadoresservice.investigadores.dominio.Investigador;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional_Valor(String correoInstitucional);

}
