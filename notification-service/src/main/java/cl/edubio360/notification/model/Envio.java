package cl.edubio360.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "envios_notificacion")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String canal;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "notificacion_id", nullable = false)
    private Notificacion notificacion;

    protected Envio() {}

    public Envio(String canal, String estado, Notificacion notificacion) {
        this.canal = canal;
        this.estado = estado;
        this.fecha = LocalDateTime.now();
        this.notificacion = notificacion;
    }

    public Long getId() { return id; }
    public String getCanal() { return canal; }
    public String getEstado() { return estado; }
    public LocalDateTime getFecha() { return fecha; }
}
