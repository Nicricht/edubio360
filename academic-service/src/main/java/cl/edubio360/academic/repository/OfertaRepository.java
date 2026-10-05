package cl.edubio360.academic.repository;

import cl.edubio360.academic.model.OfertaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfertaRepository extends JpaRepository<OfertaAcademica, Long> {
    List<OfertaAcademica> findByCarreraContainingIgnoreCaseOrSedeInstitucionContainingIgnoreCase(String carrera, String institucion);
}
