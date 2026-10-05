# Guion definitivo video EP02 — EduBío 360

Duración objetivo: **6 minutos 30 segundos**. La pauta permite entre 3 y 8 minutos.

La idea del video es demostrar el backend funcionando. No conviene leer código durante varios minutos. Lo importante es mostrar evidencia mientras se explica qué hace cada parte.

---

## Antes de grabar

Tener abierto:

1. VS Code con el repositorio en `develop`.
2. Terminal en la raíz del proyecto.
3. Docker Desktop.
4. Postman con `postman/EduBio360-EP02.postman_collection.json`.
5. Navegador con:
   - GitHub Actions;
   - Pull Request #13;
   - Swagger de Academic Service.
6. Una terminal lista para consultar MySQL.

Si el proyecto no está levantado:

```bash
docker compose up --build
```

En Windows PowerShell se puede usar exactamente el mismo comando.

---

# 0:00–0:35 — Presentación

## Mostrar

- README del repositorio.
- Tabla de microservicios y puertos.

## Decir

> “Este proyecto es EduBío 360. El objetivo es centralizar información de educación superior de la Región del Biobío y permitir trabajar con oferta académica, orientación, notificaciones, métricas e importación de datos.
>
> Para el backend usamos Java 21 con Spring Boot y Maven. La solución está separada en seis microservicios de negocio: Auth, Academic, Guidance, Notification, Analytics e Import. Además tenemos API Gateway, Eureka, RabbitMQ y MySQL.”

No enumerar librerías una por una. Basta mencionar las que aparecen después durante la demostración.

---

# 0:35–1:15 — Arquitectura y ejecución

## Mostrar

Abrir `docker-compose.yml`.

Después ejecutar:

```bash
docker compose ps
```

## Decir

> “La aplicación se levanta con Docker Compose. Aquí tengo MySQL, RabbitMQ, Eureka y los microservicios.
>
> Los servicios de negocio usan los puertos 8081 al 8086. Cada uno tiene una responsabilidad distinta y mantiene su propia persistencia lógica.”

Mostrar rápidamente que los contenedores están levantados.

No quedarse leyendo todo el archivo Docker Compose.

---

# 1:15–2:45 — CRUD REST con Postman

## Mostrar

En Postman abrir la carpeta **Academic Service**.

Ejecutar en este orden:

1. crear oferta;
2. listar;
3. obtener por id;
4. actualizar;
5. obtener inexistente;
6. crear inválido;
7. eliminar.

## Decir

Mientras se ejecutan:

> “Voy a demostrar el CRUD usando Academic Service. El recurso principal es `/api/ofertas`.”

En POST:

> “Al crear una oferta válida obtenemos HTTP 201, que indica que el recurso fue creado.”

En GET:

> “El listado devuelve 200 y el GET por id también devuelve 200 cuando el recurso existe.”

En PUT:

> “La actualización utiliza PUT y responde 200.”

En GET inexistente:

> “También manejamos errores. Si consulto un id que no existe recibo 404.”

En POST inválido:

> “Los DTO tienen validaciones con Jakarta Validation. Si envío datos incompletos se responde 400.”

En DELETE:

> “Finalmente el DELETE responde 204 porque la operación fue correcta y no necesita devolver contenido.”

Después mostrar brevemente el resultado completo de la colección:

> “La colección tiene siete casos por cada uno de los seis microservicios. Son 42 requests en total.”

No ejecutar manualmente los 42 uno por uno durante el video.

---

# 2:45–3:35 — MySQL y persistencia real

## Mostrar

En terminal:

```bash
docker compose exec mysql mysql -uroot -proot -e "SHOW DATABASES;"
```

Después:

```bash
docker compose exec mysql mysql -uroot -proot academicdb -e "SHOW TABLES;"
```

Y:

```bash
docker compose exec mysql mysql -uroot -proot academicdb -e "SELECT * FROM ofertas_academicas LIMIT 5;"
```

## Decir

> “Para esta evaluación usamos MySQL 8 como base de datos persistente.
>
> Tenemos una base lógica por microservicio: authdb, academicdb, guidancedb, notificationdb, analyticsdb e importdb.
>
> Acá se puede ver que Academic Service realmente está persistiendo en MySQL. La tabla `ofertas_academicas` fue generada y administrada mediante JPA e Hibernate.”

Si el SELECT no tiene registros porque ya se eliminó el creado en Postman, crear una oferta antes de esta parte o ejecutar el SELECT antes del DELETE.

---

# 3:35–4:25 — Capas y relaciones JPA

## Mostrar

En `academic-service/src/main/java/cl/edubio360/academic/` mostrar:

```text
controller/
service/
repository/
model/
dto/
config/
```

Luego abrir:

- `model/Sede.java`
- `model/OfertaAcademica.java`

## Decir

> “Cada microservicio está separado en capas. El controller recibe las peticiones HTTP, el service contiene la lógica, el repository trabaja con JPA y el model representa las entidades persistentes. Los DTO se usan para los datos de entrada y salida.”

Mostrar `@OneToMany` y `@ManyToOne`.

> “También tenemos relaciones JPA reales. En este caso una sede puede tener muchas ofertas académicas y cada oferta pertenece a una sede. Por eso tenemos una relación uno a muchos y su relación inversa muchos a uno mediante una clave foránea.”

No explicar todas las relaciones de los seis servicios. Una bien explicada demuestra el concepto.

---

# 4:25–5:10 — Pruebas, Cucumber y JaCoCo

## Mostrar

Abrir GitHub Actions en la ejecución verde de `develop`.

Mostrar que pasaron:

- Maven clean verify;
- Docker Compose;
- mysql-smoke.

Si se quiere demostrar desde terminal:

```bash
mvn clean verify
```

No es obligatorio esperar toda la ejecución durante la grabación si GitHub Actions ya muestra la evidencia verde.

## Decir

> “Las pruebas se ejecutan automáticamente con Maven. Tenemos JUnit y Mockito para servicios, MockMvc para controladores, Cucumber para escenarios de comportamiento y JaCoCo para cobertura.
>
> En GitHub Actions se ejecuta `mvn clean verify`, se valida Docker Compose y después se hace un smoke test real con MySQL.”

Mostrar el resultado Newman si está visible en Actions:

> “Además se ejecuta automáticamente la colección Postman con Newman. En la última ejecución fueron 42 requests, 42 assertions y cero fallos.”

---

# 5:10–5:45 — Swagger y OpenAPI

## Mostrar

Abrir:

```text
http://localhost:8082/swagger-ui/index.html
```

Expandir uno o dos endpoints de `/api/ofertas`.

## Decir

> “Cada servicio expone documentación OpenAPI con Swagger. Desde aquí podemos revisar los endpoints, parámetros, cuerpos de petición y respuestas sin tener que buscar cada ruta directamente en el código.”

Mencionar brevemente:

```text
/v3/api-docs
/v3/api-docs.yaml
/redoc.html
```

No es necesario abrir las tres.

---

# 5:45–6:15 — GitFlow

## Mostrar

En GitHub:

- rama `main`;
- rama `develop`;
- Pull Request #13 ya fusionado.

## Decir

> “Para el control de versiones usamos una estructura basada en main, develop y ramas feature.
>
> Esta implementación se trabajó en `feature/ep02-mysql-cumplimiento` y después de pasar las verificaciones se integró mediante Pull Request a develop.”

Mostrar el estado verde del PR o de Actions.

---

# 6:15–6:35 — Cierre

## Decir

> “Con esto queda demostrado el CRUD REST, la arquitectura por capas, persistencia en MySQL, relaciones JPA, validaciones y manejo de errores, pruebas automatizadas, documentación OpenAPI, Docker y trazabilidad con Git.
>
> Esa es la implementación actual del backend de EduBío 360 para la EP02.”

Terminar ahí. No agregar una conclusión larga.

---

# Preguntas que podría hacer el profesor

## ¿Por qué usan Service si ya existe Repository?

Respuesta corta:

> “Porque el repository se encarga del acceso a datos y el service concentra la lógica de negocio. Así el controller no queda mezclado con persistencia.”

## ¿Qué hace JPA?

> “JPA permite mapear objetos Java a tablas relacionales. En nuestro proyecto usamos Hibernate como implementación y JpaRepository para las operaciones de persistencia.”

## ¿Por qué MySQL?

> “Porque para esta entrega necesitábamos demostrar persistencia relacional real y reproducible. MySQL está integrado mediante perfiles de Spring y Docker Compose.”

## ¿Para qué usan H2?

> “H2 queda como perfil rápido para pruebas o desarrollo local. La persistencia evaluada se verifica con MySQL.”

## ¿Qué diferencia hay entre 400 y 404?

> “400 significa que la petición enviada no cumple lo esperado, por ejemplo por validaciones. 404 significa que el recurso solicitado no existe.”

## ¿Qué hace JaCoCo?

> “Mide qué líneas del código son ejecutadas por las pruebas y permite controlar la cobertura durante `mvn verify`.”

## ¿Qué hace MockMvc?

> “Permite probar los controladores HTTP de Spring sin tener que levantar manualmente todo el servidor.”

## ¿Qué hace Cucumber?

> “Permite expresar escenarios de comportamiento y comprobarlos automáticamente con pasos de prueba.”

## ¿Qué aporta Docker Compose?

> “Permite levantar de forma reproducible la infraestructura y los servicios con una configuración común.”

## ¿Por qué hay seis bases si usan una sola instancia MySQL?

> “Usamos una instancia de MySQL en Docker, pero cada microservicio tiene una base lógica separada. Eso mantiene separado el ownership de datos por servicio.”

---

# Checklist final

Antes de grabar verificar:

- [ ] Docker Compose levantado.
- [ ] Los seis servicios responden.
- [ ] Postman importado.
- [ ] Una oferta creada para mostrar persistencia.
- [ ] MySQL visible.
- [ ] Swagger abre en puerto 8082.
- [ ] GitHub Actions muestra ejecución verde.
- [ ] PR #13 aparece fusionado a `develop`.
- [ ] Micrófono sin saturación ni ruido fuerte.
- [ ] Video entre 6:00 y 7:00 minutos.
- [ ] Escuchar el video completo antes de entregarlo.

## Regla para hablar

No memorizar cada palabra. Memorizar la idea de cada bloque y explicar mirando lo que aparece en pantalla. Si una frase sale distinta pero técnicamente correcta, está bien.
