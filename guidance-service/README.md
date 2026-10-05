# Guidance Service

Microservicio de solicitudes de orientación académica.

## Datos principales

- Puerto: `8083`
- Recurso CRUD: `/api/solicitudes`
- Base MySQL: `guidancedb`
- Perfil por defecto: `h2`
- Perfil persistente: `mysql`
- Integraciones: Academic Service y RabbitMQ

## Endpoints

```text
GET    /api/solicitudes
GET    /api/solicitudes/{id}
POST   /api/solicitudes
PUT    /api/solicitudes/{id}
DELETE /api/solicitudes/{id}

GET    /api/solicitudes/mias
PUT    /api/solicitudes/{id}/confirmar
PUT    /api/solicitudes/{id}/cancelar
```

Las operaciones de orientación utilizan los headers `X-User-Email` y `X-User-Role`.

Guidance valida la oferta académica antes de crear una solicitud y publica un evento cuando una orientación es confirmada.

## Ejecutar

```bash
mvn -pl guidance-service spring-boot:run
```

Para el funcionamiento completo con MySQL, RabbitMQ, Eureka y Academic Service se recomienda:

```bash
docker compose up --build
```

## Pruebas

```bash
mvn -pl guidance-service test
mvn -pl guidance-service verify
```

También existen pruebas para la validación contra Academic, manejo de roles, estados y publicación de confirmaciones.

## Documentación

```text
http://localhost:8083/swagger-ui/index.html
http://localhost:8083/v3/api-docs
http://localhost:8083/redoc.html
http://localhost:8083/actuator/health
```
