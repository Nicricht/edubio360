package cl.edubio360.guidance;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record CrearSolicitudRequest(
        @NotNull Long ofertaId,
        @NotBlank @Size(max = 1000) String motivo,
        @NotNull @FutureOrPresent LocalDateTime fechaHora) {
}
