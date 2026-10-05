package cl.edubio360.importer.repository;

import cl.edubio360.importer.model.Importacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportacionRepository extends JpaRepository<Importacion, Long> {
}
