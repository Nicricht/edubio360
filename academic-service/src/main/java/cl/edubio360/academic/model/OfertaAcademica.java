package cl.edubio360.academic.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ofertas_academicas")
public class OfertaAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 180)
    private String carrera;

    @Column(nullable = false, length = 60)
    private String modalidad;

    @Column(nullable = false, length = 60)
    private String jornada;

    @Column(nullable = false, precision = 12, scale = 0)
    private BigDecimal arancel;

    @Column(nullable = false, precision = 12, scale = 0)
    private BigDecimal matricula;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;

    protected OfertaAcademica() {}

    public OfertaAcademica(String carrera, String modalidad, String jornada,
                           BigDecimal arancel, BigDecimal matricula, Sede sede) {
        this.carrera = carrera;
        this.modalidad = modalidad;
        this.jornada = jornada;
        this.arancel = arancel;
        this.matricula = matricula;
        this.sede = sede;
    }

    public void actualizar(String carrera, String modalidad, String jornada,
                           BigDecimal arancel, BigDecimal matricula, Sede sede) {
        this.carrera = carrera;
        this.modalidad = modalidad;
        this.jornada = jornada;
        this.arancel = arancel;
        this.matricula = matricula;
        this.sede = sede;
    }

    public Long getId() { return id; }
    public String getCarrera() { return carrera; }
    public String getModalidad() { return modalidad; }
    public String getJornada() { return jornada; }
    public BigDecimal getArancel() { return arancel; }
    public BigDecimal getMatricula() { return matricula; }
    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }
}
