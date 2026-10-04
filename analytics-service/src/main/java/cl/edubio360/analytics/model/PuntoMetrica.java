package cl.edubio360.analytics.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "puntos_metrica")
public class PuntoMetrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String etiqueta;

    @Column(nullable = false)
    private Double valor;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "metrica_id", nullable = false)
    private Metrica metrica;

    protected PuntoMetrica() {}

    public PuntoMetrica(String etiqueta, Double valor, Metrica metrica) {
        this.etiqueta = etiqueta;
        this.valor = valor;
        this.metrica = metrica;
    }

    public Long getId() { return id; }
    public String getEtiqueta() { return etiqueta; }
    public Double getValor() { return valor; }
}
