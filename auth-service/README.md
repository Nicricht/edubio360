# Auth Service

Microservicio de autenticación y administración de usuarios de EduBío 360.

## Datos principales

- Puerto: `8081`
- Recurso CRUD: `/api/usuarios`
- Autenticación: `/api/auth`
- Base MySQL: `authdb`
- Perfil por defecto: `h2`
- Perfil persistente: `mysql`

## Endpoints

```text
POST   /api/auth/register
POST   /api/auth/login

GET    /api/usuarios
GET    /api/usuarios/{id}
POST   /api/usuarios
PUT    /api/usuarios/{id}
DELETE /api/usuarios/{id}
```

El login genera un JWT con el correo y el rol principal del usuario.

## Ejecutar

Desde la raíz del proyecto:

```bash
mvn -pl auth-service spring-boot:run
```

Para usar MySQL:

```bash
SPRING_PROFILES_ACTIVE=mysql DB_HOST=localhost DB_PORT=3306 DB_NAME=authdb DB_USER=root DB_PASSWORD=root mvn -pl auth-service spring-boot:run
```

También se puede levantar junto al resto del sistema con:

```bash
docker compose up --build
```

## Pruebas

```bash
mvn -pl auth-service test
mvn -pl auth-service verify
```

Las pruebas incluyen JUnit, Mockito, MockMvc, Cucumber y cobertura con JaCoCo.

## Documentación

Con el servicio levantado:

```text
http://localhost:8081/swagger-ui/index.html
http://localhost:8081/v3/api-docs
http://localhost:8081/redoc.html
http://localhost:8081/actuator/health
```
