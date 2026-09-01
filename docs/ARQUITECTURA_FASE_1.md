# Arquitectura implementada - Fase 1

## Objetivo

La primera fase prioriza un corte vertical demostrable sobre el flujo de orientación, manteniendo los límites de dominio definidos para EduBío 360.

## Implementado

- Spring Cloud Gateway como entrada única.
- Eureka para registro y descubrimiento.
- Auth Service con BCrypt y JWT.
- Academic Service con catálogo mínimo consultable.
- Guidance Service con creación, consulta propia, cancelación y confirmación.
- Validación síncrona Guidance -> Academic mediante OpenFeign.
- Resilience4j sobre la validación de Academic.
- RabbitMQ para publicar `orientacion.confirmada`, con retry básico y DLQ en el consumidor de notificaciones.
- Notification Service como consumidor del evento.
- Analytics Service e Import Service con endpoints mínimos para mantener sus fronteras de dominio.
- Actuator en todos los servicios.
- Docker Compose para levantar la arquitectura local.

## Persistencia local

La fase 1 usa H2 en Auth, Academic y Guidance para que el repositorio pueda ejecutarse de forma inmediata. Esto no reemplaza la decisión de Oracle del diseño final. La migración prevista mantiene ownership lógico por schema:

- `AUTH_SCHEMA`
- `ACADEMIC_SCHEMA`
- `GUIDANCE_SCHEMA`
- `ANALYTICS_SCHEMA`

## Pendiente para siguientes fases

- Modelo Oracle definitivo y scripts DDL/3FN.
- Administración completa de usuarios y roles.
- Estados REPROGRAMADA, RECHAZADA, COMPLETADA e INASISTENCIA con historial formal.
- Informes de orientación.
- Políticas de reintento/DLQ extendidas para otros eventos y consumidores.
- ETL real del Excel de matrículas.
- Read model y cálculos reales de Analytics.
- OpenAPI y pruebas de integración más amplias.
- Despliegue en AWS.
