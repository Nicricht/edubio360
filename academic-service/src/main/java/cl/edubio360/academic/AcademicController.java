package cl.edubio360.academic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AcademicController {
    private final OfertaRepository ofertas;

    public AcademicController(OfertaRepository ofertas) {
        this.ofertas = ofertas;
    }

    @GetMapping("/ofertas")
    public List<OfertaAcademica> listar(@RequestParam(required = false) String q) {
        if (q == null || q.isBlank()) {
            return ofertas.findAll();
        }
        String term = q.trim();
        return ofertas.findByCarreraContainingIgnoreCaseOrInstitucionContainingIgnoreCase(term, term);
    }

    @GetMapping("/ofertas/{id}")
    public OfertaAcademica detalle(@PathVariable("id") Long id) {
        return ofertas.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oferta académica no encontrada"));
    }

    @GetMapping("/sedes")
    public List<SedeResponse> sedes() {
        return ofertas.findAll().stream()
                .map(o -> new SedeResponse(o.getSede(), o.getInstitucion(), o.getComuna()))
                .distinct()
                .toList();
    }

    public record SedeResponse(String sede, String institucion, String comuna) {}
}
