# Import Service

Microservicio que registra procesos de importación y valida archivos de carga.

## Datos principales

- Puerto: `8086`
- Recurso CRUD: `/api/importaciones`
- Carga de archivo: `/api/importaciones/archivo`
- Base MySQL: `importdb`
- Perfil por defecto: `h2`
- Perfil persistente: `mysql`
- Tamaño máximo configurado: 25 MB

## Endpoints

```text
GET    /api/importaciones
GET    /api/importaciones/{id}
POST   /api/importaciones
PUT    /api/importaciones/{id}
DELETE /api/importaciones/{id}

POST   /api/importaciones/archivo
```

La carga de archivos requiere el header:

```text
X-User-Role: ADMIN
```

El endpoint de archivo acepta carga multipart y valida archivos admitidos por el servicio.

## Ejecutar

```bash
mvn -pl import-service spring-boot:run
```

Con MySQL:

```bash
docker compose up --build
```

## Pruebas

```bash
mvn -pl import-service test
mvn -pl import-service verify
```

## Documentación

```text
http://localhost:8086/swagger-ui/index.html
http://localhost:8086/v3/api-docs
http://localhost:8086/redoc.html
http://localhost:8086/actuator/health
```
