package cl.edubio360.academic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record OfertaRequest(
        @NotBlank String carrera,
        @NotBlank String modalidad,
        @NotBlank String jornada,
        @NotNull @PositiveOrZero BigDecimal arancel,
        @NotNull @PositiveOrZero BigDecimal matricula,
        @NotNull Long sedeId) {
}
