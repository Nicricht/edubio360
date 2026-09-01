package cl.edubio360.guidance;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudOrientacionTest {
    @Test
    void confirmaSolicitudPendiente() {
        SolicitudOrientacion solicitud = new SolicitudOrientacion(
                "student@edubio.local", 1L, "Necesito orientación", LocalDateTime.now().plusDays(1));

        solicitud.confirmar("orientador@edubio.local");

        assertEquals("CONFIRMADA", solicitud.getEstado());
        assertEquals("orientador@edubio.local", solicitud.getOrientadorEmail());
    }

    @Test
    void noPermiteConfirmarUnaSolicitudCancelada() {
        SolicitudOrientacion solicitud = new SolicitudOrientacion(
                "student@edubio.local", 1L, "Necesito orientación", LocalDateTime.now().plusDays(1));
        solicitud.cancelar();

        assertThrows(IllegalStateException.class,
                () -> solicitud.confirmar("orientador@edubio.local"));
    }
}
