package cl.edubio360.guidance;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class GuidanceController {
    private final GuidanceService service;

    public GuidanceController(GuidanceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudOrientacion crear(@RequestHeader("X-User-Email") String email,
                                      @RequestHeader("X-User-Role") String role,
                                      @Valid @RequestBody CrearSolicitudRequest request) {
        return service.crear(email, role, request);
    }

    @GetMapping("/mias")
    public List<SolicitudOrientacion> mias(@RequestHeader("X-User-Email") String email,
                                           @RequestHeader("X-User-Role") String role) {
        return service.mias(email, role);
    }

    @PutMapping("/{id}/confirmar")
    public SolicitudOrientacion confirmar(@PathVariable("id") Long id,
                                          @RequestHeader("X-User-Email") String email,
                                          @RequestHeader("X-User-Role") String role) {
        return service.confirmar(id, email, role);
    }

    @PutMapping("/{id}/cancelar")
    public SolicitudOrientacion cancelar(@PathVariable("id") Long id,
                                         @RequestHeader("X-User-Email") String email,
                                         @RequestHeader("X-User-Role") String role) {
        return service.cancelar(id, email, role);
    }
}
