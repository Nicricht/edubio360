package cl.edubio360.analytics.service;

import cl.edubio360.analytics.dto.MetricaRequest;
import cl.edubio360.analytics.model.Metrica;
import cl.edubio360.analytics.repository.MetricaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {
    @Mock MetricaRepository repository;
    AnalyticsService service;
    Metrica entity;

    @BeforeEach
    void setUp() {
        service = new AnalyticsService(repository);
        entity = new Metrica("arancel_promedio", "Descripción");
        entity.addPunto("Tecnología", 3000000d);
    }

    @Test
    void crudCompleto() {
        when(repository.findAll()).thenReturn(List.of(entity));
        assertEquals(1, service.listar().size());

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        assertSame(entity, service.obtener(1L));

        when(repository.save(any(Metrica.class))).thenAnswer(i -> i.getArgument(0));
        MetricaRequest request = new MetricaRequest("mediana", "Nueva métrica",
                List.of(new MetricaRequest.PuntoRequest("Tecnología", 3100000d)));

        assertEquals("mediana", service.crear(request).getNombre());
        assertEquals("mediana", service.actualizar(1L, request).getNombre());
        assertEquals(1, entity.getPuntos().size());

        service.eliminar(1L);
        verify(repository).delete(entity);
    }

    @Test
    void notFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.obtener(99L));
    }
}
