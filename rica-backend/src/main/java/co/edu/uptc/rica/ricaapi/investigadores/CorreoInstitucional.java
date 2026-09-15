package co.edu.uptc.rica.ricaapi.investigadores;

import jakarta.persistence.Embeddable;

@Embeddable 
public record CorreoInstitucional(String valor) {

    public CorreoInstitucional {   
        if(valor == null || !valor.endsWith("@uptc.edu.co")){
            throw new IllegalArgumentException("El correo institucional debe terminar con @uptc.edu.co");
        }
    }

}
