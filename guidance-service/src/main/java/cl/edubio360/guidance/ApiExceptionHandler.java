package cl.edubio360.guidance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<Map<String, Object>> responseStatus(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(body(
                ex.getStatusCode().value(), ex.getStatusCode().toString(), ex.getReason()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Datos inválidos");
        return ResponseEntity.badRequest().body(body(400, "VALIDATION_ERROR", message));
    }

    private Map<String, Object> body(int status, String code, String message) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", OffsetDateTime.now().toString());
        response.put("status", status);
        response.put("code", code);
        response.put("message", message);
        return response;
    }
}
