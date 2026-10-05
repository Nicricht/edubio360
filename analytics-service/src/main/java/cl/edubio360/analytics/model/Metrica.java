package cl.edubio360.analytics.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metricas")
public class Metrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @OneToMany(mappedBy = "metrica", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PuntoMetrica> puntos = new ArrayList<>();

    protected Metrica() {}

    public Metrica(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void actualizar(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void addPunto(String etiqueta, Double valor) {
        puntos.add(new PuntoMetrica(etiqueta, valor, this));
    }

    public void replacePuntos(List<PuntoMetrica> nuevos) {
        puntos.clear();
        if (nuevos != null) {
            nuevos.forEach(p -> addPunto(p.getEtiqueta(), p.getValor()));
        }
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public List<PuntoMetrica> getPuntos() { return puntos; }
}
