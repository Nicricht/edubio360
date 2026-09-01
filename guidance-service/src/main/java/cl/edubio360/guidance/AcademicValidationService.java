package cl.edubio360.guidance;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AcademicValidationService {
    private final AcademicClient academicClient;

    public AcademicValidationService(AcademicClient academicClient) {
        this.academicClient = academicClient;
    }

    @Retry(name = "academicService")
    @CircuitBreaker(name = "academicService", fallbackMethod = "fallback")
    public AcademicClient.OfertaResponse validarOferta(Long ofertaId) {
        return academicClient.obtenerOferta(ofertaId);
    }

    public AcademicClient.OfertaResponse fallback(Long ofertaId, Throwable error) {
        throw new ResponseStatusException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "No fue posible validar la oferta académica en este momento",
                error);
    }
}
