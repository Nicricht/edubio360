# Guía de video EP02 — EduBío 360

Duración objetivo: **6 a 7 minutos**. La pauta permite 3–8 minutos.

> El video debe demostrar el sistema real. No basta leer el README o mostrar código estático.

## 0:00–0:30 — Presentación

- Nombre del proyecto: EduBío 360.
- Problema: centralizar y comparar información de Educación Superior del Biobío y apoyar orientación.
- Backend: Java 21 + Spring Boot.
- Seis microservicios de negocio: Auth, Academic, Guidance, Notification, Analytics e Import.
- Infraestructura: Gateway, Eureka, RabbitMQ, MySQL 8 y Docker Compose.

Frase técnica sugerida:

“EduBío 360 está construido como un backend de microservicios. Para esta evaluación usamos MySQL 8 como persistencia relacional y mantenemos H2 solamente para pruebas rápidas.”

## 0:30–1:20 — Clonar y levantar

Mostrar:

```bash
git clone <URL-DEL-REPO>
cd edubio360
docker compose up --build
```

Explicar que Docker Compose levanta:

- MySQL 8.
- RabbitMQ.
- Eureka.
- Auth Service.
- Academic Service.
- Guidance Service.
- Notification Service.
- Analytics Service.
- Import Service.
- API Gateway.

Mostrar que los servicios quedan arriba y, si hay tiempo, abrir Eureka o Actuator.

## 1:20–3:20 — CRUD y manejo de errores en Postman

Importar:

```text
postman/EduBio360-EP02.postman_collection.json
```

Demostrar al menos en un servicio:

1. POST válido → **201**.
2. GET lista → **200**.
3. GET por id → **200**.
4. PUT → **200**.
5. DELETE → **204**.
6. POST inválido → **400** con detalle JSON.
7. GET inexistente → **404**.

Después mostrar rápidamente uno o dos casos de otro microservicio.

Explicar:

“Las validaciones se realizan con Jakarta Validation y los errores se homogeneizan con `@RestControllerAdvice`, por eso el cliente recibe respuestas JSON consistentes.”

## 3:20–4:20 — MySQL y persistencia

Mostrar `application-mysql.yml` de un servicio y señalar:

- URL JDBC MySQL.
- driver `com.mysql.cj.jdbc.Driver`.
- variables `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`.

Luego mostrar MySQL con una tabla creada por JPA y un registro creado desde Postman.

Explicar:

“Cada microservicio mantiene ownership de sus datos. En Docker usamos una instancia MySQL con bases lógicas separadas por servicio.”

Bases:

```text
authdb
academicdb
guidancedb
notificationdb
analyticsdb
importdb
```

## 4:20–5:05 — Relaciones JPA y arquitectura en capas

Mostrar un ejemplo de relación, por ejemplo:

```text
Sede 1 ---- N OfertaAcademica
```

y en código:

- `@OneToMany(mappedBy = ...)`
- `@ManyToOne`
- `@JoinColumn`
- `cascade`
- `orphanRemoval`

Después mostrar la estructura:

```text
controller/
service/
repository/
model/
dto/
config/
```

Explicar brevemente la responsabilidad de cada capa.

## 5:05–5:45 — Maven, pruebas y JAR

Ejecutar:

```bash
mvn clean verify
mvn clean package
```

Mostrar **BUILD SUCCESS**.

Mostrar los JAR en `target/`.

Explicar que las pruebas incluyen:

- JUnit.
- Mockito.
- MockMvc.
- Cucumber.
- JaCoCo.

## 5:45–6:20 — Swagger / OpenAPI

Abrir, por ejemplo:

```text
http://localhost:8082/swagger-ui/index.html
```

Mostrar endpoints y esquemas.

También mencionar:

```text
/v3/api-docs
/v3/api-docs.yaml
/redoc.html
```

## 6:20–6:50 — Git y cierre

Mostrar:

- `main`
- `develop`
- `feature/ep02-mysql-cumplimiento`
- Pull Request de integración.

Cerrar con:

“Con esto demostramos endpoints REST funcionales, arquitectura en capas, persistencia MySQL, relaciones JPA, validaciones, manejo homogéneo de errores, pruebas automatizadas, documentación OpenAPI, empaquetado Maven, Docker y trazabilidad Git.”

## Audio

Para IE12:

- usar micrófono cercano;
- grabar en un lugar silencioso;
- evitar música;
- hablar a velocidad normal;
- no leer literalmente todo el guion;
- mostrar cada evidencia mientras se explica.

## Checklist antes de grabar

- `docker compose up --build` funciona.
- Postman importado.
- MySQL visible y con tablas.
- `mvn clean verify` en verde.
- JAR generado.
- Swagger abre.
- GitHub muestra ramas y PR.
- Video entre 3 y 8 minutos.
