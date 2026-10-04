package cl.edubio360.academic.dto;

import java.math.BigDecimal;

public record OfertaResponse(
        Long id,
        String carrera,
        String institucion,
        Long sedeId,
        String sede,
        String comuna,
        String modalidad,
        String jornada,
        BigDecimal arancel,
        BigDecimal matricula) {
}
