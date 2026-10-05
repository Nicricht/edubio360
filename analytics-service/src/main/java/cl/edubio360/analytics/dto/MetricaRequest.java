package cl.edubio360.analytics.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record MetricaRequest(
        @NotBlank @Size(max = 120) String nombre,
        @NotBlank @Size(max = 500) String descripcion,
        @NotNull List<@Valid PuntoRequest> puntos) {

    public record PuntoRequest(
            @NotBlank @Size(max = 120) String etiqueta,
            @NotNull Double valor) {
    }
}
