package cl.edubio360.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NotificacionRequest(
        @NotBlank @Size(max = 60) String tipo,
        @NotBlank @Size(max = 180) String destinatario,
        @NotBlank @Size(max = 1000) String mensaje) {
}
