package cl.edubio360.guidance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_orientacion")
public class SolicitudOrientacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estudianteEmail;

    @Column(nullable = false)
    private Long ofertaId;

    @Column(nullable = false, length = 1000)
    private String motivo;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false, length = 30)
    private String estado;

    private String orientadorEmail;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected SolicitudOrientacion() {}

    public SolicitudOrientacion(String estudianteEmail, Long ofertaId, String motivo, LocalDateTime fechaHora) {
        this.estudianteEmail = estudianteEmail;
        this.ofertaId = ofertaId;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.estado = "PENDIENTE";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void confirmar(String orientadorEmail) {
        if (!estado.equals("PENDIENTE") && !estado.equals("REPROGRAMADA")) {
            throw new IllegalStateException("La solicitud no puede confirmarse desde el estado " + estado);
        }
        this.estado = "CONFIRMADA";
        this.orientadorEmail = orientadorEmail;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancelar() {
        if (estado.equals("COMPLETADA") || estado.equals("CANCELADA") || estado.equals("RECHAZADA")) {
            throw new IllegalStateException("La solicitud no puede cancelarse desde el estado " + estado);
        }
        this.estado = "CANCELADA";
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getEstudianteEmail() { return estudianteEmail; }
    public Long getOfertaId() { return ofertaId; }
    public String getMotivo() { return motivo; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getEstado() { return estado; }
    public String getOrientadorEmail() { return orientadorEmail; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
