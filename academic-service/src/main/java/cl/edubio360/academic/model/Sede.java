package cl.edubio360.academic.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sedes")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 180)
    private String nombre;

    @Column(nullable = false, length = 180)
    private String institucion;

    @Column(nullable = false, length = 120)
    private String comuna;

    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OfertaAcademica> ofertas = new ArrayList<>();

    protected Sede() {}

    public Sede(String nombre, String institucion, String comuna) {
        this.nombre = nombre;
        this.institucion = institucion;
        this.comuna = comuna;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getInstitucion() { return institucion; }
    public String getComuna() { return comuna; }
    public List<OfertaAcademica> getOfertas() { return ofertas; }

    public void addOferta(OfertaAcademica oferta) {
        ofertas.add(oferta);
        oferta.setSede(this);
    }

    public void replaceOfertas(List<OfertaAcademica> nuevas) {
        ofertas.clear();
        if (nuevas != null) nuevas.forEach(this::addOferta);
    }
}
