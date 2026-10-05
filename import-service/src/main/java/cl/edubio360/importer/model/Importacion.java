package cl.edubio360.importer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "importaciones")
public class Importacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String archivo;

    @Column(nullable = false, length = 40)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "importacion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ErrorImportacion> errores = new ArrayList<>();

    protected Importacion() {}

    public Importacion(String archivo, String estado) {
        this.archivo = archivo;
        this.estado = estado;
        this.createdAt = LocalDateTime.now();
    }

    public void actualizar(String archivo, String estado) {
        this.archivo = archivo;
        this.estado = estado;
    }

    public void addError(Integer fila, String mensaje) {
        errores.add(new ErrorImportacion(fila, mensaje, this));
    }

    public Long getId() { return id; }
    public String getArchivo() { return archivo; }
    public String getEstado() { return estado; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<ErrorImportacion> getErrores() { return errores; }
}
