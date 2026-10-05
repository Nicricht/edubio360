# Arquitectura implementada - Fase 1 / EP02

## Objetivo

EduBío 360 mantiene los límites de dominio definidos originalmente y adapta su implementación a la pauta EP02 de JVY0101. La evaluación se construye sobre el sistema existente: no se reemplaza el dominio ni se crean microservicios artificiales.

## Componentes

- Spring Cloud Gateway como entrada única.
- Eureka para registro y descubrimiento.
- Auth Service con BCrypt y JWT.
- Academic Service para catálogo académico.
- Guidance Service para solicitudes de orientación.
- Notification Service para notificaciones y consumo de eventos.
- Analytics Service para métricas.
- Import Service para procesos de importación.
- OpenFeign y Resilience4j entre Guidance y Academic.
- RabbitMQ para eventos asíncronos.
- Actuator en los servicios.
- Docker Compose para ejecución local reproducible.

## Persistencia

La decisión vigente para el backend es **MySQL 8**.

Cada microservicio de negocio mantiene ownership de sus datos mediante una base lógica propia dentro de la instancia MySQL utilizada por Docker Compose:

- `authdb`
- `academicdb`
- `guidancedb`
- `notificationdb`
- `analyticsdb`
- `importdb`

Para pruebas y ejecución rápida se conserva el perfil **H2 en memoria**, configurado en modo compatible con MySQL. H2 no sustituye la persistencia evaluada; MySQL es el motor relacional de la EP02 y del backend vigente.

## Estructura por servicio

Los microservicios evaluables siguen la organización:

```text
controller/
service/
repository/
model/
dto/
config/
```

Los controladores exponen REST, los servicios contienen lógica de negocio, los repositorios usan Spring Data JPA, los modelos representan persistencia y relaciones, y `config/` centraliza manejo de errores y configuración propia del servicio.

## Relaciones JPA implementadas

- Auth: Usuario -> Roles.
- Academic: Sede -> Ofertas académicas.
- Guidance: Solicitud -> Historial.
- Notification: Notificación -> Envíos.
- Analytics: Métrica -> Puntos de métrica.
- Import: Importación -> Errores de importación.

Las relaciones usan `@OneToMany` / `@ManyToOne`, `mappedBy`, `@JoinColumn`, cascade y orphan removal donde corresponde.

## Prioridad EP02

La prioridad hasta cerrar la evaluación es:

1. CRUD REST completo.
2. MySQL persistente.
3. Relaciones JPA.
4. Validaciones y errores homogéneos.
5. OpenAPI/Swagger.
6. Postman.
7. Pruebas automáticas y JaCoCo.
8. Docker Compose.
9. JAR ejecutable.
10. README/evidencias.
11. Video final.
