package cl.edubio360.guidance;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "academic-service")
public interface AcademicClient {
    @GetMapping("/api/ofertas/{id}")
    OfertaResponse obtenerOferta(@PathVariable("id") Long id);

    record OfertaResponse(Long id, String carrera, String institucion, String sede, String comuna,
                          String modalidad, String jornada, BigDecimal arancel, BigDecimal matricula) {}
}
