# Estado de Cumplimiento EP02 — EduBío 360

Este documento resume el estado real de la entrega según la pauta consolidada en `PAUTA_EVALUACION_EP02_JVY0101.md`.

La verificación final de backend se ejecutó en GitHub Actions sobre los seis microservicios de negocio. El flujo `EP02 Verify Java 21` terminó correctamente con Maven, JaCoCo, Docker Compose, MySQL y la colección Postman ejecutada con Newman.

| IE | Peso | Estado | Evidencia verificada |
|---|---:|---|---|
| IE1 | 20% | CUMPLIDO | CRUD REST en los 6 servicios con respuestas 200/201/204/400/404, validaciones y OpenAPI |
| IE2 | 10% | CUMPLIDO | Capas controller/service/repository/model/dto/config e inyección por constructor |
| IE6 | 5% | CUMPLIDO | Relaciones JPA OneToMany/ManyToOne implementadas en los 6 dominios |
| IE8 | 5% | CUMPLIDO | Web, JPA, Validation, H2, MySQL, Springdoc, JUnit, Mockito, MockMvc, Cucumber, Surefire y JaCoCo |
| IE9 | 10% | CUMPLIDO | main, develop, feature/*, commits descriptivos y Pull Request |
| IE3 | 10% | CUMPLIDO | Colección Postman con 42 requests ejecutadas por Newman: 42 requests, 42 assertions, 0 fallos |
| IE4 | 10% | CUMPLIDO | MySQL 8, perfiles application-mysql.yml, seis bases lógicas y Docker Compose |
| IE5 | 10% | CUMPLIDO | CRUD JPA persistente verificado contra MySQL en los 6 servicios |
| IE7 | 5% | CUMPLIDO | `mvn clean verify` exitoso, JAR generados y JaCoCo verificado |
| IE10 | 5% | CUMPLIDO | README raíz + README por cada microservicio, Docker Compose, scripts, puertos, endpoints, perfiles, pruebas y documentación OpenAPI/ReDoc |
| IE11 | 5% | PENDIENTE | Grabar video técnico de 3 a 8 minutos |
| IE12 | 5% | PENDIENTE | Revisar calidad de audio del video antes de entregar |

## Resultado técnico verificado

La ejecución de CI confirmó:

- `mvn clean verify`: correcto.
- Validación de `docker compose`: correcta.
- Smoke test de los 6 microservicios: correcto.
- MySQL: `authdb`, `academicdb`, `guidancedb`, `notificationdb`, `analyticsdb` e `importdb` disponibles.
- Tablas JPA principales verificadas en las seis bases.
- Postman/Newman: 42 requests ejecutadas, 42 test scripts, 42 assertions y 0 fallos.

## Lo único pendiente para la entrega

La parte técnica del repositorio queda cerrada para EP02. Falta producir la evidencia audiovisual:

1. grabar el video siguiendo `GUIA_VIDEO_EP02.md`;
2. mostrar Postman, MySQL, Maven/JAR, Swagger y Git;
3. comprobar que el video dure entre 3 y 8 minutos;
4. escuchar el video completo antes de entregar para asegurar audio claro.

## Base de datos

MySQL 8 es la base de datos del backend vigente. H2 queda solamente como perfil rápido para pruebas y desarrollo.
