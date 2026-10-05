package cl.edubio360.academic.controller;

import cl.edubio360.academic.dto.OfertaRequest;
import cl.edubio360.academic.dto.OfertaResponse;
import cl.edubio360.academic.dto.SedeResponse;
import cl.edubio360.academic.service.AcademicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AcademicController {
    private final AcademicService service;

    public AcademicController(AcademicService service) {
        this.service = service;
    }

    @GetMapping("/ofertas")
    public List<OfertaResponse> listar(@RequestParam(required = false) String q) {
        return service.listar(q);
    }

    @GetMapping("/ofertas/{id}")
    public OfertaResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/ofertas")
    @ResponseStatus(HttpStatus.CREATED)
    public OfertaResponse crear(@Valid @RequestBody OfertaRequest request) {
        return service.crear(request);
    }

    @PutMapping("/ofertas/{id}")
    public OfertaResponse actualizar(@PathVariable Long id, @Valid @RequestBody OfertaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/ofertas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/sedes")
    public List<SedeResponse> sedes() {
        return service.listarSedes();
    }
}
