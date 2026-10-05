package cl.edubio360.guidance;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcademicValidationServiceTest {

    @Test
    void devuelveOfertaCuandoAcademicResponde() {
        AcademicClient client = mock(AcademicClient.class);
        AcademicValidationService service = new AcademicValidationService(client);

        AcademicClient.OfertaResponse oferta = new AcademicClient.OfertaResponse(
                1L,
                "Ingeniería Informática",
                "Institución Demo",
                "Sede Centro",
                "Concepción",
                "Presencial",
                "Diurna",
                new BigDecimal("3000000"),
                new BigDecimal("180000"));

        when(client.obtenerOferta(1L)).thenReturn(oferta);

        assertSame(oferta, service.validarOferta(1L));
        verify(client).obtenerOferta(1L);
    }

    @Test
    void fallbackDevuelveServicioNoDisponible() {
        AcademicClient client = mock(AcademicClient.class);
        AcademicValidationService service = new AcademicValidationService(client);
        RuntimeException cause = new RuntimeException("academic no disponible");

        ResponseStatusException error = assertThrows(
                ResponseStatusException.class,
                () -> service.fallback(99L, cause));

        assertEquals(503, error.getStatusCode().value());
        assertEquals("No fue posible validar la oferta académica en este momento", error.getReason());
        assertSame(cause, error.getCause());
    }
}
