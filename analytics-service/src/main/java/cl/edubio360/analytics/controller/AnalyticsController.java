package cl.edubio360.analytics.controller;

import cl.edubio360.analytics.dto.MetricaRequest;
import cl.edubio360.analytics.model.Metrica;
import cl.edubio360.analytics.service.AnalyticsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsController {
    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/api/metricas")
    public List<Metrica> listar() { return service.listar(); }

    @GetMapping("/api/metricas/{id}")
    public Metrica obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping("/api/metricas")
    @ResponseStatus(HttpStatus.CREATED)
    public Metrica crear(@Valid @RequestBody MetricaRequest request) { return service.crear(request); }

    @PutMapping("/api/metricas/{id}")
    public Metrica actualizar(@PathVariable Long id, @Valid @RequestBody MetricaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/api/metricas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }

    @GetMapping("/api/analytics/resumen")
    public Map<String, Object> resumen() {
        return Map.of(
                "estado", "OPERATIVO",
                "metricasPersistidas", service.listar().size(),
                "motorEp02", "MySQL 8"
        );
    }
}
