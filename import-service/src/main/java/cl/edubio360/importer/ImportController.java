package cl.edubio360.importer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/importaciones")
public class ImportController {
    @PostMapping
    public Map<String, Object> validar(@RequestParam("file") MultipartFile file,
                                       @RequestHeader(value = "X-User-Role", required = false) String role) {
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La importación requiere rol ADMIN");
        }
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo está vacío");
        }
        String filename = file.getOriginalFilename() == null ? "archivo" : file.getOriginalFilename();
        String lower = filename.toLowerCase(Locale.ROOT);
        if (!lower.endsWith(".xlsx") && !lower.endsWith(".csv")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato no soportado. Use .xlsx o .csv");
        }
        return Map.of(
                "estado", "VALIDADO_FASE_1",
                "archivo", filename,
                "bytes", file.getSize(),
                "fecha", OffsetDateTime.now().toString(),
                "siguientePaso", "Transformación y carga a staging en una fase posterior"
        );
    }
}
