package cl.edubio360.academic.config;

import cl.edubio360.academic.model.OfertaAcademica;
import cl.edubio360.academic.model.Sede;
import cl.edubio360.academic.repository.OfertaRepository;
import cl.edubio360.academic.repository.SedeRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class SeedData {
    @Bean
    ApplicationRunner seedAcademicData(SedeRepository sedes, OfertaRepository ofertas) {
        return args -> {
            if (ofertas.count() > 0) return;
            Sede concepcion = sedes.save(new Sede("Sede Concepción", "Institución Demo Biobío", "Concepción"));
            Sede talcahuano = sedes.save(new Sede("Sede Talcahuano", "Instituto Regional Demo", "Talcahuano"));
            ofertas.save(new OfertaAcademica("Ingeniería Informática", "Presencial", "Diurna",
                    new BigDecimal("3200000"), new BigDecimal("180000"), concepcion));
            ofertas.save(new OfertaAcademica("Analista Programador", "Presencial", "Vespertina",
                    new BigDecimal("2450000"), new BigDecimal("160000"), concepcion));
            ofertas.save(new OfertaAcademica("Ingeniería en Administración", "Presencial", "Vespertina",
                    new BigDecimal("2800000"), new BigDecimal("170000"), talcahuano));
        };
    }
}
