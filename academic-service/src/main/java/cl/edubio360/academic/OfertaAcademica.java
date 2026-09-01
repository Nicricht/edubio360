package cl.edubio360.academic;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ofertas_academicas")
public class OfertaAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String carrera;
    @Column(nullable = false)
    private String institucion;
    @Column(nullable = false)
    private String sede;
    @Column(nullable = false)
    private String comuna;
    @Column(nullable = false)
    private String modalidad;
    @Column(nullable = false)
    private String jornada;
    @Column(nullable = false, precision = 12, scale = 0)
    private BigDecimal arancel;
    @Column(nullable = false, precision = 12, scale = 0)
    private BigDecimal matricula;

    protected OfertaAcademica() {}

    public OfertaAcademica(String carrera, String institucion, String sede, String comuna,
                           String modalidad, String jornada, BigDecimal arancel, BigDecimal matricula) {
        this.carrera = carrera;
        this.institucion = institucion;
        this.sede = sede;
        this.comuna = comuna;
        this.modalidad = modalidad;
        this.jornada = jornada;
        this.arancel = arancel;
        this.matricula = matricula;
    }

    public Long getId() { return id; }
    public String getCarrera() { return carrera; }
    public String getInstitucion() { return institucion; }
    public String getSede() { return sede; }
    public String getComuna() { return comuna; }
    public String getModalidad() { return modalidad; }
    public String getJornada() { return jornada; }
    public BigDecimal getArancel() { return arancel; }
    public BigDecimal getMatricula() { return matricula; }
}
