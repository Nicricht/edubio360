package cl.edubio360.guidance.repository;

import cl.edubio360.guidance.model.SolicitudOrientacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<SolicitudOrientacion, Long> {
    List<SolicitudOrientacion> findByEstudianteEmailOrderByCreatedAtDesc(String estudianteEmail);
}
