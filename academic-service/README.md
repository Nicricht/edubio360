# Academic Service

Microservicio que administra la oferta académica y las sedes de EduBío 360.

## Datos principales

- Puerto: `8082`
- Recurso CRUD: `/api/ofertas`
- Consulta de sedes: `/api/sedes`
- Base MySQL: `academicdb`
- Perfil por defecto: `h2`
- Perfil persistente: `mysql`

## Endpoints

```text
GET    /api/ofertas
GET    /api/ofertas?q=texto
GET    /api/ofertas/{id}
POST   /api/ofertas
PUT    /api/ofertas/{id}
DELETE /api/ofertas/{id}

GET    /api/sedes
```

La relación principal del dominio es una sede con muchas ofertas académicas.

## Ejecutar

Desde la raíz:

```bash
mvn -pl academic-service spring-boot:run
```

Para usar MySQL:

```bash
SPRING_PROFILES_ACTIVE=mysql DB_HOST=localhost DB_PORT=3306 DB_NAME=academicdb DB_USER=root DB_PASSWORD=root mvn -pl academic-service spring-boot:run
```

Ejecución completa:

```bash
docker compose up --build
```

## Pruebas

```bash
mvn -pl academic-service test
mvn -pl academic-service verify
```

## Documentación

```text
http://localhost:8082/swagger-ui/index.html
http://localhost:8082/v3/api-docs
http://localhost:8082/redoc.html
http://localhost:8082/actuator/health
```
