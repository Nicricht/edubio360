package cl.edubio360.academic;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class SeedData {
    @Bean
    ApplicationRunner seedAcademicData(OfertaRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            repository.save(new OfertaAcademica(
                    "Ingeniería Informática", "Institución Demo Biobío", "Sede Concepción", "Concepción",
                    "Presencial", "Diurna", new BigDecimal("3200000"), new BigDecimal("180000")));
            repository.save(new OfertaAcademica(
                    "Analista Programador", "Institución Demo Biobío", "Sede Concepción", "Concepción",
                    "Presencial", "Vespertina", new BigDecimal("2450000"), new BigDecimal("160000")));
            repository.save(new OfertaAcademica(
                    "Ingeniería en Administración", "Instituto Regional Demo", "Sede Talcahuano", "Talcahuano",
                    "Presencial", "Vespertina", new BigDecimal("2800000"), new BigDecimal("170000")));
        };
    }
}
