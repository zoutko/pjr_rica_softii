package co.edu.uptc.rica.ricaapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class Investigador {

    private Long id;
    private String nombreCompleto;
    private String correoInstitucional;
    private String grupoInvestigacion;

    public Investigador() {
    }

    public Investigador(Long id, String nombreCompleto, String correoInstitucional, String grupoInvestigacion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correoInstitucional = correoInstitucional;
        this.grupoInvestigacion = grupoInvestigacion;
    }
}
