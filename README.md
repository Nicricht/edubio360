# EduBío 360 - Microservicios (Fase 1)

Base funcional mínima de la arquitectura de EduBío 360 con Java 21, Spring Boot 3, Spring Cloud Gateway, Eureka, RabbitMQ y servicios separados por dominio.

## Servicios

| Componente | Puerto | Responsabilidad de esta fase |
|---|---:|---|
| discovery-server | 8761 | Registro y descubrimiento con Eureka |
| api-gateway | 8080 | Entrada única, rutas y validación básica de JWT |
| auth-service | 8081 | Registro, login, BCrypt y emisión de JWT |
| academic-service | 8082 | Catálogo mínimo de ofertas y sedes |
| guidance-service | 8083 | Crear, listar, cancelar y confirmar solicitudes |
| notification-service | 8084 | Consume eventos de orientación desde RabbitMQ |
| analytics-service | 8085 | Endpoint mínimo de indicadores |
| import-service | 8086 | Validación inicial de archivos de importación |
| RabbitMQ | 5672 / 15672 | Mensajería asíncrona |

## Alcance

Esta es una primera fase funcional. El objetivo es demostrar la separación de servicios, Gateway, Eureka, comunicación REST, Circuit Breaker y mensajería asíncrona. El modelo Oracle definitivo, seguridad granular completa, ETL real, analítica completa y despliegue AWS quedan para las siguientes fases.

Para que el repositorio pueda ejecutarse sin instalar Oracle, Auth, Academic y Guidance usan H2 en memoria por defecto. Los tres módulos incluyen además `application-oracle.yml` y el driver JDBC de Oracle para activar posteriormente los schemas reales definidos en el diseño.

## Requisitos

- Java 21 + Maven 3.6.3 o superior, o
- Docker + Docker Compose

## Opción recomendada: Docker Compose

1. Copiar `.env.example` a `.env`.
2. Cambiar `JWT_SECRET` por un valor local de al menos 32 caracteres.
3. Ejecutar:

```bash
docker compose up --build
```

Eureka: `http://localhost:8761`

Gateway: `http://localhost:8080`

RabbitMQ UI: `http://localhost:15672`

## Credenciales de demostración

Se crean únicamente para probar el flujo local:

| Rol | Correo | Contraseña |
|---|---|---|
| STUDENT | student@edubio.local | Student123! |
| ORIENTADOR | orientador@edubio.local | Orientador123! |
| ADMIN | admin@edubio.local | Admin123! |

No deben reutilizarse en un entorno real.

## Flujo mínimo que se puede demostrar

1. Iniciar sesión como estudiante.
2. Consultar `GET /api/ofertas`.
3. Crear `POST /api/solicitudes` con una oferta válida.
4. Iniciar sesión como orientador.
5. Confirmar la solicitud.
6. Guidance publica `orientacion.confirmada` en RabbitMQ.
7. Notification Service consume el evento.

En Windows se incluye `scripts/test-flow.ps1` para ejecutar esta prueba de punta a punta.

## Rutas principales a través del Gateway

- `POST /api/auth/login`
- `POST /api/auth/register`
- `GET /api/ofertas`
- `GET /api/ofertas/{id}`
- `GET /api/sedes`
- `POST /api/solicitudes`
- `GET /api/solicitudes/mias`
- `PUT /api/solicitudes/{id}/confirmar`
- `PUT /api/solicitudes/{id}/cancelar`
- `GET /api/notificaciones`
- `GET /api/analytics/resumen`
- `POST /api/importaciones`

## Ejecución sin Docker

Compilar desde la raíz:

```bash
mvn clean package
```

Después iniciar en este orden:

1. discovery-server
2. RabbitMQ
3. auth-service, academic-service, notification-service, analytics-service, import-service
4. guidance-service
5. api-gateway

Cada módulo se puede iniciar con:

```bash
mvn -pl nombre-del-modulo spring-boot:run
```

## Perfil Oracle y ownership

En esta fase local se usa H2 para facilitar una demostración reproducible. Para usar Oracle, activa el perfil `oracle` y define `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` y, si corresponde, `DB_SCHEMA`. Ejemplo:

```bash
SPRING_PROFILES_ACTIVE=oracle DB_URL=jdbc:oracle:thin:@//localhost:1521/FREEPDB1 DB_USERNAME=ACADEMIC_SCHEMA DB_PASSWORD=... mvn -pl academic-service spring-boot:run
```

El diseño final mantiene:

- Auth Service -> `AUTH_SCHEMA`
- Academic Service -> `ACADEMIC_SCHEMA`
- Guidance Service -> `GUIDANCE_SCHEMA`
- Analytics Service -> `ANALYTICS_SCHEMA`
- Import Service -> staging/control de importaciones

Un servicio no debe consultar directamente tablas pertenecientes a otro dominio.
