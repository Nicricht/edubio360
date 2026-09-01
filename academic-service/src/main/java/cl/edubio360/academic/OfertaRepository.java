package cl.edubio360.academic;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfertaRepository extends JpaRepository<OfertaAcademica, Long> {
    List<OfertaAcademica> findByCarreraContainingIgnoreCaseOrInstitucionContainingIgnoreCase(String carrera, String institucion);
}
