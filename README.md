# EduBío 360 — Backend de Microservicios

Backend de EduBío 360 para exploración, comparación y orientación sobre Educación Superior del Biobío.

La implementación EP02 se construye sobre la arquitectura existente y se alinea con `docs/PAUTA_EVALUACION_EP02_JVY0101.md`.

## Stack

- Java 21
- Spring Boot 3
- Maven
- Spring Data JPA / Hibernate
- MySQL 8
- H2 para pruebas rápidas
- Spring Validation
- Springdoc OpenAPI / Swagger
- Spring Cloud Gateway
- Eureka
- OpenFeign
- Resilience4j
- RabbitMQ
- JUnit / Mockito / MockMvc
- Cucumber
- JaCoCo
- Docker / Docker Compose

## Microservicios

| Servicio | Puerto | Recurso principal |
|---|---:|---|
| Auth Service | 8081 | `/api/usuarios` |
| Academic Service | 8082 | `/api/ofertas` |
| Guidance Service | 8083 | `/api/solicitudes` |
| Notification Service | 8084 | `/api/notificaciones` |
| Analytics Service | 8085 | `/api/metricas` |
| Import Service | 8086 | `/api/importaciones` |
| API Gateway | 8080 | entrada única |
| Discovery Server | 8761 | Eureka |

## Base de datos

MySQL 8 es el motor persistente del backend.

Docker Compose crea una base lógica por microservicio:

```text
authdb
academicdb
guidancedb
notificationdb
analyticsdb
importdb
```

El perfil H2 existe para pruebas y ejecución rápida. La configuración Oracle anterior ya no forma parte de la decisión vigente del backend.

## Perfiles

Por defecto:

```bash
mvn spring-boot:run
```

usa el perfil `h2`.

Para MySQL:

```bash
SPRING_PROFILES_ACTIVE=mysql \
DB_HOST=localhost \
DB_PORT=3306 \
DB_NAME=academicdb \
DB_USER=root \
DB_PASSWORD=root \
mvn spring-boot:run
```

En Windows PowerShell se pueden definir las variables con `$env:NOMBRE="valor"`.

## Ejecución completa

Copiar variables de ejemplo:

```bash
cp .env.example .env
```

Levantar infraestructura y servicios:

```bash
docker compose up --build
```

Detener:

```bash
docker compose down
```

Eliminar también el volumen MySQL:

```bash
docker compose down -v
```

## Contrato CRUD EP02

Cada microservicio evaluable expone un recurso principal con el patrón:

```text
GET    /api/<recurso>       -> 200
GET    /api/<recurso>/{id}  -> 200 / 404
POST   /api/<recurso>       -> 201 / 400
PUT    /api/<recurso>/{id}  -> 200 / 400 / 404
DELETE /api/<recurso>/{id}  -> 204 / 404
```

Las respuestas de error se homogeneizan mediante `@RestControllerAdvice`.

## Swagger y OpenAPI

En cada servicio:

```text
http://localhost:<puerto>/swagger-ui/index.html
http://localhost:<puerto>/v3/api-docs
http://localhost:<puerto>/v3/api-docs.yaml
```

## Pruebas

Verificación completa desde la raíz:

```bash
mvn clean verify
```

Empaquetado:

```bash
mvn clean package
```

Los reportes JaCoCo se generan dentro de `target/site/jacoco/` de los módulos que ejecutan pruebas.

## Postman

La colección EP02 se almacena en:

```text
postman/EduBio360-EP02.postman_collection.json
```

Debe cubrir, por servicio:

1. crear -> 201;
2. listar -> 200;
3. obtener por id -> 200;
4. obtener inexistente -> 404;
5. crear inválido -> 400;
6. actualizar -> 200;
7. eliminar -> 204.

## GitFlow

- `main`: versión estable.
- `develop`: integración.
- `feature/*`: funcionalidades y tareas.
- `hotfix/*`: correcciones urgentes.

La EP02 se desarrolla mediante ramas `feature/*` y Pull Requests hacia `develop`.

## Documentación por microservicio

Cada servicio de negocio tiene su propio README con puerto, endpoints, perfiles de base de datos, comandos de ejecución, pruebas y rutas de documentación:

- [Auth Service](auth-service/README.md)
- [Academic Service](academic-service/README.md)
- [Guidance Service](guidance-service/README.md)
- [Notification Service](notification-service/README.md)
- [Analytics Service](analytics-service/README.md)
- [Import Service](import-service/README.md)

## Pauta y trazabilidad

- Pauta consolidada: `docs/PAUTA_EVALUACION_EP02_JVY0101.md`
- Arquitectura actual: `docs/ARQUITECTURA_FASE_1.md`
- Estado de cumplimiento: `docs/CUMPLIMIENTO_EP02.md`

La regla de trabajo es:

```text
pauta -> estado real -> implementación -> prueba -> evidencia
```

Una funcionalidad no se marca como cumplida solamente porque exista código. Debe existir evidencia verificable.
