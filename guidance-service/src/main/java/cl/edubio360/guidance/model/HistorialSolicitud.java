package cl.edubio360.guidance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_solicitud")
public class HistorialSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(nullable = false, length = 500)
    private String detalle;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private SolicitudOrientacion solicitud;

    protected HistorialSolicitud() {}

    public HistorialSolicitud(String estado, String detalle, SolicitudOrientacion solicitud) {
        this.estado = estado;
        this.detalle = detalle;
        this.fecha = LocalDateTime.now();
        this.solicitud = solicitud;
    }

    public Long getId() { return id; }
    public String getEstado() { return estado; }
    public String getDetalle() { return detalle; }
    public LocalDateTime getFecha() { return fecha; }
}
