package cl.edubio360.analytics.repository;

import cl.edubio360.analytics.model.Metrica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricaRepository extends JpaRepository<Metrica, Long> {
}
