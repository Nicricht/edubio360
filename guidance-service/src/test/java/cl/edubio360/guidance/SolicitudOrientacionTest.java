package cl.edubio360.guidance;

import cl.edubio360.guidance.model.SolicitudOrientacion;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudOrientacionTest {
    @Test
    void confirmaSolicitudPendiente() {
        SolicitudOrientacion solicitud = new SolicitudOrientacion(
                "student@example.test", 1L, "Necesito orientación", LocalDateTime.now().plusDays(1));

        solicitud.confirmar("orientador@example.test");

        assertEquals("CONFIRMADA", solicitud.getEstado());
        assertEquals("orientador@example.test", solicitud.getOrientadorEmail());
        assertEquals(2, solicitud.getHistorial().size());
    }

    @Test
    void noPermiteConfirmarUnaSolicitudCancelada() {
        SolicitudOrientacion solicitud = new SolicitudOrientacion(
                "student@example.test", 1L, "Necesito orientación", LocalDateTime.now().plusDays(1));
        solicitud.cancelar();

        assertThrows(IllegalStateException.class,
                () -> solicitud.confirmar("orientador@example.test"));
    }
}
