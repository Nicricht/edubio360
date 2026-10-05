# Analytics Service

Microservicio de métricas de EduBío 360.

## Datos principales

- Puerto: `8085`
- Recurso CRUD: `/api/metricas`
- Resumen: `/api/analytics/resumen`
- Base MySQL: `analyticsdb`
- Perfil por defecto: `h2`
- Perfil persistente: `mysql`

## Endpoints

```text
GET    /api/metricas
GET    /api/metricas/{id}
POST   /api/metricas
PUT    /api/metricas/{id}
DELETE /api/metricas/{id}

GET    /api/analytics/resumen
```

Una métrica puede tener varios puntos asociados mediante el mapeo JPA del dominio.

## Ejecutar

```bash
mvn -pl analytics-service spring-boot:run
```

Con MySQL:

```bash
docker compose up --build
```

## Pruebas

```bash
mvn -pl analytics-service test
mvn -pl analytics-service verify
```

## Documentación

```text
http://localhost:8085/swagger-ui/index.html
http://localhost:8085/v3/api-docs
http://localhost:8085/redoc.html
http://localhost:8085/actuator/health
```
