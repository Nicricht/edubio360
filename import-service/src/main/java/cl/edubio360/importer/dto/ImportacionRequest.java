package cl.edubio360.importer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ImportacionRequest(
        @NotBlank @Size(max = 255) String archivo,
        @NotBlank @Size(max = 40) String estado,
        @NotNull List<@Valid ErrorRequest> errores) {

    public record ErrorRequest(
            @NotNull @PositiveOrZero Integer fila,
            @NotBlank @Size(max = 500) String mensaje) {
    }
}
