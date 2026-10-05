# Notification Service

Microservicio encargado de registrar notificaciones y sus envíos.

## Datos principales

- Puerto: `8084`
- Recurso CRUD: `/api/notificaciones`
- Base MySQL: `notificationdb`
- Perfil por defecto: `h2`
- Perfil persistente: `mysql`
- Integración: RabbitMQ

## Endpoints

```text
GET    /api/notificaciones
GET    /api/notificaciones/{id}
POST   /api/notificaciones
PUT    /api/notificaciones/{id}
DELETE /api/notificaciones/{id}
```

El servicio también consume eventos enviados por RabbitMQ para registrar notificaciones relacionadas con el proceso de orientación.

## Ejecutar

```bash
mvn -pl notification-service spring-boot:run
```

Para probar la integración con RabbitMQ y MySQL:

```bash
docker compose up --build
```

## Pruebas

```bash
mvn -pl notification-service test
mvn -pl notification-service verify
```

Se prueban el CRUD, los controladores y el consumidor de eventos.

## Documentación

```text
http://localhost:8084/swagger-ui/index.html
http://localhost:8084/v3/api-docs
http://localhost:8084/redoc.html
http://localhost:8084/actuator/health
```
