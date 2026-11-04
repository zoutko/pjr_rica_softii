package co.edu.uptc.rica.investigadoresservice.investigadores.infraestructura.salida.mensajeria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter 
@Table(name = "outbox_event")
public class OutboxEvent {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topico;

    @Column(nullable = false)
    private String clave;

    @Column (nullable = false, length = 4000)
    private String payload;

    @Column(name = "creado_en", nullable = false)
    private Long creadoEn;

    @Column(nullable = false)
    private boolean publicado;

    public OutboxEvent() {
    }

    public OutboxEvent(String topico, String clave, String payload, Long creadoEn, boolean publicado) {
        this.topico = topico;
        this.clave = clave;
        this.payload = payload;
        this.creadoEn = creadoEn;
        this.publicado = publicado;
    }

    public void marcarPublicado() {
        this.publicado = true;
    }
}
