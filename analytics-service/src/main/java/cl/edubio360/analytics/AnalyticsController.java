package cl.edubio360.analytics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    @GetMapping("/resumen")
    public Map<String, Object> resumen() {
        return Map.of(
                "fase", "FASE_1",
                "estado", "OPERATIVO",
                "indicadoresDisponibles", List.of(
                        "aranceles_por_area",
                        "orientaciones_por_periodo",
                        "inasistencias"),
                "detalle", "Los cálculos estadísticos y el read model definitivo se incorporan en la siguiente fase."
        );
    }
}
