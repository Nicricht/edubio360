package cl.edubio360.importer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "errores_importacion")
public class ErrorImportacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer fila;

    @Column(nullable = false, length = 500)
    private String mensaje;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "importacion_id", nullable = false)
    private Importacion importacion;

    protected ErrorImportacion() {}

    public ErrorImportacion(Integer fila, String mensaje, Importacion importacion) {
        this.fila = fila;
        this.mensaje = mensaje;
        this.importacion = importacion;
    }

    public Long getId() { return id; }
    public Integer getFila() { return fila; }
    public String getMensaje() { return mensaje; }
}
