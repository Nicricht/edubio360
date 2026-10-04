# Estado de Cumplimiento EP02 — EduBío 360

Este documento se actualiza contra la pauta oficial consolidada en `PAUTA_EVALUACION_EP02_JVY0101.md`.

| IE | Peso | Estado actual | Evidencia objetivo |
|---|---:|---|---|
| IE1 | 20% | EN CONSTRUCCIÓN | CRUD REST 200/201/204/400/404 + @Valid + OpenAPI en los 6 servicios |
| IE2 | 10% | AVANZADO | controller/service/repository/model/dto/config e inyección por constructor |
| IE6 | 5% | AVANZADO | relaciones JPA reales en los 6 dominios |
| IE8 | 5% | AVANZADO | Web/JPA/Validation/H2/MySQL/Springdoc/Test/Cucumber/JaCoCo |
| IE9 | 10% | AVANZADO | main/develop/feature/*, commits y PR |
| IE3 | 10% | EN CONSTRUCCIÓN | colección Postman + GlobalExceptionHandler |
| IE4 | 10% | AVANZADO | MySQL 8 + application-mysql.yml + Docker Compose |
| IE5 | 10% | EN CONSTRUCCIÓN | CRUD JPA persistente en los 6 servicios |
| IE7 | 5% | PENDIENTE DE VERIFICACIÓN | mvn clean package + .jar ejecutable |
| IE10 | 5% | AVANZADO | README reproducible + Docker Compose + scripts |
| IE11 | 5% | PENDIENTE | video 3–8 min |
| IE12 | 5% | PENDIENTE | audio claro |

## Regla de cierre

Un IE solo pasa a **CUMPLIDO** después de verificar su evidencia en ejecución. El código no se considera prueba suficiente por sí solo.

## Decisión de base de datos

MySQL 8 reemplaza a Oracle en el backend vigente. H2 queda únicamente como perfil de pruebas/desarrollo rápido.
