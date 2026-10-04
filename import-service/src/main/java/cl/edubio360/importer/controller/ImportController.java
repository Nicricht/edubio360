package cl.edubio360.importer.controller;

import cl.edubio360.importer.dto.ImportacionRequest;
import cl.edubio360.importer.model.Importacion;
import cl.edubio360.importer.service.ImportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/importaciones")
public class ImportController {
    private final ImportService service;

    public ImportController(ImportService service) {
        this.service = service;
    }

    @GetMapping
    public List<Importacion> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Importacion obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Importacion crear(@Valid @RequestBody ImportacionRequest request) { return service.crear(request); }

    @PutMapping("/{id}")
    public Importacion actualizar(@PathVariable Long id, @Valid @RequestBody ImportacionRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }

    @PostMapping(value = "/archivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Importacion validarArchivo(@RequestParam("file") MultipartFile file,
                                      @RequestHeader(value = "X-User-Role", required = false) String role) {
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La importación requiere rol ADMIN");
        }
        return service.validarArchivo(file);
    }
}
