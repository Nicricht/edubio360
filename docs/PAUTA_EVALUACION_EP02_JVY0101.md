# Pauta de Evaluación EP02 — JVY0101

> [!IMPORTANT]
> **DOCUMENTO MAESTRO PARA CUALQUIER CHAT NUEVO, INTEGRANTE O DOCENTE.**
> Antes de proponer cambios, responder qué falta, preparar una defensa oral o explicar el proyecto, leer este archivo completo y contrastarlo con el estado real del repositorio.
> Este documento contiene qué evalúa la EP02, cómo se aplica a EduBío 360, qué evidencia existe, qué debe mostrarse y qué conceptos debe dominar el estudiante para defender el trabajo.

---

## 0. Instrucciones de uso para otro chat o asistente

Si otro chat recibe este repositorio y debe ayudar con la EP02, debe trabajar en este orden:

1. Leer este archivo completo.
2. Inspeccionar el repositorio real, especialmente README.md, docker-compose.yml, pom.xml, los seis microservicios, postman/, scripts/ y .github/workflows/ci.yml.
3. Relacionar cada decisión con el Indicador de Evaluación correspondiente.
4. No cambiar arquitectura, base de datos o cantidad de microservicios sin una razón respaldada por la pauta.
5. No inventar requisitos basándose únicamente en TicketWave. TicketWave es referencia técnica, no el dominio que debe copiarse.
6. Si se detecta una diferencia entre este documento y el repositorio, manda el repositorio real y debe actualizarse este Markdown.
7. Antes de agregar código, determinar si la pauta realmente lo exige.
8. Para enseñar al estudiante, explicar desde cero y relacionar cada concepto con código real de EduBío 360.
9. Cuando se prepare una defensa oral, practicar preguntas y respuestas, no entregar solamente frases para memorizar.
10. Diferenciar siempre entre requisito obligatorio, evidencia existente, mejora opcional y evidencia que debe mostrar el estudiante.

### Modo de enseñanza esperado

Cuando el objetivo sea que el estudiante aprenda a defender el proyecto:

- explicar primero el concepto;
- mostrar dónde aparece en EduBío 360;
- explicar por qué existe;
- seguir el flujo real de una petición;
- enseñar qué pregunta podría hacer el profesor;
- hacer que el estudiante responda con sus palabras;
- corregir la respuesta técnica;
- evitar asumir conocimientos previos.

El objetivo es que el estudiante pueda defender el sistema aunque el profesor cambie la pregunta. No debe limitarse a memorizar un guion.

---

## 0.1 Estado certificado actual de EduBío 360

Repositorio: Nicricht/edubio360.

Rama de entrega: main.

Estado técnico certificado al cerrar esta EP02:

- 6 microservicios de negocio implementados;
- CRUD REST en los 6;
- relaciones JPA en los 6;
- perfiles H2/MySQL;
- MySQL 8 mediante Docker Compose;
- colección Postman ejecutada con Newman;
- 42 requests, 42 assertions y 0 fallos en la certificación final conocida;
- JUnit + Mockito;
- MockMvc;
- Cucumber;
- JaCoCo;
- Swagger/OpenAPI;
- ReDoc;
- README raíz y README por microservicio;
- GitFlow con main, develop y feature/*;
- CI de EP02 en GitHub Actions;
- generación de JAR con Maven.

La ejecución final certificada sobre main fue EP02 Verify Java 21 #160, commit ebe4c51162fba7660966315caee986a4a0a5747d, con verify y mysql-smoke terminados correctamente.

> Si el repositorio cambia después de ese commit, un chat nuevo debe volver a verificar el CI y no asumir que esta certificación representa el HEAD actual.

### Estado por indicador

| IE | Estado técnico en el repositorio | Evidencia que debe saber mostrar/explicar |
|---|---|---|
| IE1 | Cumplido | CRUD REST, @Valid y Swagger |
| IE2 | Cumplido | controller -> service -> repository -> model/config |
| IE3 | Cumplido y ejecutado | Postman/Newman y errores 400/404 |
| IE4 | Cumplido | application-mysql.yml, driver y MySQL real |
| IE5 | Cumplido | dato persistido mediante JPA/MySQL |
| IE6 | Cumplido | OneToMany, ManyToOne, JoinColumn, cascade, orphanRemoval |
| IE7 | Cumplido | mvn clean package, JAR y ejecución |
| IE8 | Cumplido | dependencias/plugins principales del pom.xml |
| IE9 | Cumplido | main, develop, feature/*, commits y PR |
| IE10 | Cumplido | README, Docker Compose, scripts y ejecución reproducible |
| IE11 | No certificable solo desde Git | video técnico de 3 a 8 minutos |
| IE12 | No certificable solo desde Git | audio claro y estable |

---

## 0.2 Arquitectura que debe reconocer otro chat

Los seis microservicios de negocio son:

| Servicio | Puerto | Recurso principal | Responsabilidad |
|---|---:|---|---|
| Auth Service | 8081 | /api/usuarios | usuarios, registro, login y JWT |
| Academic Service | 8082 | /api/ofertas | oferta académica y sedes |
| Guidance Service | 8083 | /api/solicitudes | solicitudes de orientación |
| Notification Service | 8084 | /api/notificaciones | notificaciones y envíos |
| Analytics Service | 8085 | /api/metricas | métricas y puntos de métricas |
| Import Service | 8086 | /api/importaciones | procesos de importación y errores |

Componentes de infraestructura que no se cuentan como microservicios de negocio para el cálculo del 80%:

- API Gateway;
- Discovery Server / Eureka;
- RabbitMQ;
- MySQL.

### Regla del 80%

6 microservicios de negocio × 0,80 = 4,8. Por lo tanto, el mínimo entero es 5.

El repositorio implementa 6 de 6. El ejemplo '8 microservicios -> mínimo 7' pertenece a TicketWave y no obliga a inventar dos servicios adicionales.

---

## 0.3 Mapa mental para explicar el backend al profesor

Flujo principal:

    Cliente / Postman
           |
           v
       Controller
           |
           v
        Service
           |
           v
       Repository
           |
           v
     JPA / Hibernate
           |
           v
         MySQL

Responsabilidades:

- Controller: recibe la petición HTTP y expone endpoints.
- Service: concentra la lógica de negocio.
- Repository: abstrae el acceso a datos.
- JPA/Hibernate: mapea objetos Java con tablas relacionales.
- MySQL: almacena los datos de forma persistente.
- DTO: representa entrada/salida y permite validar sin acoplar directamente la API a la entidad.
- GlobalExceptionHandler: homogeneiza respuestas de error.

Herramientas alrededor del flujo:

- Postman/Newman: prueba la API.
- Swagger/OpenAPI: documenta la API.
- JUnit: ejecuta pruebas Java.
- Mockito: simula dependencias.
- MockMvc: prueba controladores HTTP.
- Cucumber: expresa escenarios de comportamiento.
- JaCoCo: mide cobertura.
- Maven: compila, prueba y empaqueta.
- Docker Compose: levanta el entorno.
- Git/GitHub: versiona y demuestra trazabilidad.

---

## 0.4 Preguntas que otro chat debe practicar con el estudiante

Un chat nuevo debe preparar al estudiante, como mínimo, para responder:

- ¿Qué pide la pauta?
- ¿Qué hicieron en esta evaluación?
- ¿Qué es un microservicio?
- ¿Qué responsabilidad tiene cada uno de los seis servicios?
- ¿Qué es REST?
- ¿Qué es CRUD?
- ¿Qué significan 200, 201, 204, 400 y 404?
- ¿Qué hace un Controller?
- ¿Qué hace un Service?
- ¿Qué hace un Repository?
- ¿Por qué no se llama al Repository directamente desde el Controller?
- ¿Qué es un DTO?
- ¿Qué hace @Valid?
- ¿Qué hace @RestControllerAdvice?
- ¿Qué es JPA?
- ¿Qué es Hibernate?
- ¿Qué significa OneToMany?
- ¿Qué significa ManyToOne?
- ¿Qué hace @JoinColumn?
- ¿Qué significan cascade y orphanRemoval?
- ¿Qué diferencia hay entre H2 y MySQL?
- ¿Qué hace Maven?
- ¿Qué es un JAR?
- ¿Qué hace Docker Compose?
- ¿Qué prueban JUnit, Mockito, MockMvc, Cucumber y JaCoCo?
- ¿Qué hace Postman/Newman?
- ¿Qué hace RabbitMQ?
- ¿Qué hace Eureka?
- ¿Qué hace el API Gateway?
- ¿Cómo saben que el sistema funciona?
- ¿Cómo se relaciona la evidencia mostrada con cada IE?

### Método de práctica

1. Hacer una pregunta.
2. Esperar la respuesta del estudiante.
3. Marcar qué parte estuvo correcta.
4. Detectar conceptos incorrectos o incompletos.
5. Explicar desde cero usando el código real.
6. Pedir que responda nuevamente con sus palabras.
7. Aumentar progresivamente la dificultad.

Orden sugerido de estudio:

1. pauta y seis microservicios;
2. REST, CRUD y códigos HTTP;
3. Controller, Service, Repository y DTO;
4. JPA, Hibernate y relaciones;
5. MySQL, H2, Docker Compose, Maven y JAR;
6. pruebas y cobertura;
7. Gateway, Eureka, RabbitMQ e integración;
8. simulacro completo de defensa.

---

## 0.5 Qué NO debe hacer otro chat

- No agregar microservicios para llegar a ocho.
- No volver a Oracle para esta EP02.
- No sustituir MySQL por otra base sin una nueva instrucción oficial.
- No inventar requisitos que no aparecen en la pauta.
- No afirmar que una prueba existe sin inspeccionar repositorio o CI.
- No afirmar que IE11/IE12 están cumplidos solo porque exista un guion.
- No confundir Gateway/Eureka/RabbitMQ con los seis microservicios de negocio.
- No agregar complejidad antes de verificar si aporta evidencia evaluada.
- No enseñar únicamente con definiciones abstractas. Usar código real del repositorio.

---

## 0.6 Qué revisar si el usuario pregunta '¿qué falta?'

Comprobar en este orden:

1. HEAD actual de main.
2. Último CI de EP02.
3. Estado de los seis microservicios.
4. Colección Postman.
5. Configuración MySQL/H2.
6. Docker Compose.
7. JPA y relaciones.
8. Pruebas y JaCoCo.
9. Generación de JAR.
10. Ramas y Pull Requests.
11. Documentación.
12. Evidencia audiovisual.

Solo después de esa auditoría se debe afirmar que algo está cumplido.

---

## Propósito de este documento

Este archivo consolida la pauta y las instrucciones visibles en el material entregado por el docente para la Evaluación Parcial N°2 de JVY0101, y fija cómo deben interpretarse dentro de EduBío 360.

**Regla principal del proyecto:** la EP02 se construye sobre lo que EduBío 360 ya tenía definido e implementado. No se reemplaza el dominio ni se transforma el proyecto en TicketWave. TicketWave se utiliza únicamente como referencia técnica del material docente. Cuando exista una exigencia explícita de la pauta, esa exigencia gobierna la entrega EP02 y se adapta al dominio real de EduBío 360.

**Regla para responder preguntas de la evaluación:** toda respuesta técnica o decisión sobre EP02 debe fundamentarse primero en esta pauta, identificar el Indicador de Evaluación (IE) correspondiente y después explicar cómo se aplica a la arquitectura existente de EduBío 360. No se deben inventar requisitos ausentes de la pauta.

---

## 1. Datos generales de la entrega

- Evaluación: Evaluación Parcial N°2.
- Asignatura: JVY0101.
- Tema: desarrollo y presentación de microservicios implementados.
- Fecha de entrega indicada en el material: **06/10/2026**.
- Alcance mínimo: **al menos el 80% de los microservicios definidos en el diseño**.
- El material docente muestra como ejemplo: **8 microservicios -> mínimo 7**.
- Encargo: **50%**.
- Presentación: **50%**.
- Presentación en video: **3 a 8 minutos**.
- Forma de entrega indicada: enlaces en AVA y copia al correo del docente.

### Aplicación del alcance a EduBío 360

El diseño actual de EduBío 360 define seis microservicios de negocio:

1. Auth Service.
2. Academic Service.
3. Guidance Service.
4. Notification Service.
5. Analytics Service.
6. Import Service.

Además existen componentes de infraestructura, como API Gateway y Discovery Server/Eureka.

La frase normativa de la pauta es "80% de los microservicios definidos en el diseño". Por lo tanto, si se consideran los seis microservicios de negocio definidos para EduBío 360, el cálculo derivado es:

- 80% de 6 = 4,8.
- El mínimo entero que satisface el porcentaje es **5 microservicios**.

El ejemplo "8 -> mínimo 7" pertenece al material docente mostrado. No se deben crear microservicios artificiales solo para copiar TicketWave. Si el docente determina que el diseño evaluable de EduBío 360 contiene ocho microservicios, entonces se aplicará literalmente el mínimo de siete.

---

## 2. Matriz oficial de Indicadores de Evaluación

| IE | Peso | Exigencia observada en la pauta | Evidencia esperada |
|---|---:|---|---|
| IE1 | 20% | Implementa endpoints RESTful funcionales con Spring Boot | Cada microservicio evaluado expone GET lista, GET por id, POST con 201, PUT con 200 y DELETE con 204; validación con @Valid; documentación OpenAPI/Swagger |
| IE2 | 10% | Arquitectura interna en capas y buenas prácticas | Estructura controller / service / repository / model + config; inyección de dependencias por constructor |
| IE6 | 5% | Mapeo JPA/Hibernate y relaciones OneToMany/ManyToOne | Relación bidireccional de agregado con @OneToMany(mappedBy=...), cascade=ALL, orphanRemoval=true y @ManyToOne + @JoinColumn |
| IE8 | 5% | Configuración de pom.xml | Dependencias y plugins requeridos para web, JPA, validation, H2, MySQL, Springdoc, testing, Cucumber, Spring Boot Maven Plugin, Surefire y JaCoCo |
| IE9 | 10% | Repositorio Git versionado con ramas y commits | Uso verificable de main, develop, feature/*, commits descriptivos y .gitignore |
| IE3 | 10% | Verificación con Postman y manejo de errores | Colección Postman con casos felices y de error; @RestControllerAdvice / GlobalExceptionHandler con respuestas homogéneas |
| IE4 | 10% | Conexión a base de datos relacional | Perfil mysql mediante application-mysql.yml, conexión JDBC MySQL, mysql-connector-j en pom.xml y docker-compose levantando MySQL |
| IE5 | 10% | Entidades, repositorios y controladores con CRUD persistente | Entidades JPA con relaciones, JpaRepository por agregado, service y controller con CRUD completo y persistencia efectiva |
| IE7 | 5% | Comandos Maven y artefacto .jar válido | mvn clean package genera un fat jar ejecutable; mvn test / mvn verify ejecutan pruebas y validan cobertura |
| IE10 | 5% | Clonar, instalar dependencias y ejecutar localmente | README raíz y documentación por servicio con requisitos, puertos, endpoints, perfiles de BD, pruebas y comandos reproducibles |
| IE11 | 5% | Video ordenado, con hilo conductor y lenguaje técnico | Demostración técnica de 3 a 8 minutos siguiendo una secuencia comprensible |
| IE12 | 5% | Audio claro y sin ruido | Voz entendible, volumen estable, buena modulación y ambiente silencioso |

**Total: 100%.**

### Distribución por dimensión

**Encargo: 50%**

- IE1: 20%.
- IE2: 10%.
- IE6: 5%.
- IE8: 5%.
- IE9: 10%.

**Presentación: 50%**

- IE3: 10%.
- IE4: 10%.
- IE5: 10%.
- IE7: 5%.
- IE10: 5%.
- IE11: 5%.
- IE12: 5%.

---

## 3. Contrato REST mínimo por microservicio evaluado

El material de referencia establece el siguiente contrato CRUD:

| Método | Ruta conceptual | Resultado esperado |
|---|---|---|
| GET | /api/<recurso> | Lista recursos, incluyendo sus hijos cuando corresponda |
| GET | /api/<recurso>/{id} | Obtiene recurso por id, 200 |
| POST | /api/<recurso> | Crea recurso, 201 |
| PUT | /api/<recurso>/{id} | Actualiza recurso, 200 |
| DELETE | /api/<recurso>/{id} | Elimina recurso, 204 |

Errores que deben manejarse de forma homogénea mediante @RestControllerAdvice:

- 400 por validación, con detalle por campo.
- 400 por JSON malformado.
- 404 por recurso inexistente.

La ruta y el nombre del recurso deben pertenecer al dominio real de cada servicio de EduBío 360.

---

## 4. Persistencia y relaciones JPA

La pauta exige persistencia efectiva y relaciones JPA/Hibernate.

Patrón observado en el material:

- Agregado padre con @OneToMany.
- mappedBy configurado correctamente.
- cascade = CascadeType.ALL.
- orphanRemoval = true.
- Relación hija con @ManyToOne.
- @JoinColumn para la clave foránea.
- Manejo coherente de la colección en creación y actualización.

### Regla de EduBío 360

Cada relación debe representar una relación real del dominio. No se crearán tablas o entidades ficticias solo para satisfacer una anotación.

Ejemplos naturales que pueden utilizarse al implementar la pauta:

- Institución -> Sedes.
- Carrera -> Ofertas académicas.
- Solicitud de orientación -> Historial de estados.
- Solicitud de orientación -> Informe/recomendaciones.
- Importación -> Errores o filas procesadas.
- Notificación -> Intentos/envíos, cuando esa persistencia forme parte del servicio.

La relación concreta de cada servicio debe validarse contra el modelo existente antes de implementarse.

---

## 5. Base de datos: H2 y MySQL para EP02

El material docente utiliza dos perfiles:

| Perfil | Motor | Uso |
|---|---|---|
| h2 | H2 en memoria | Pruebas y ejecución rápida |
| mysql | MySQL 8 | Ejecución relacional real con Docker Compose |

Archivos esperados en el ejemplo:

- application.yml.
- application-h2.yml.
- application-mysql.yml.

Variables observadas para el perfil MySQL:

- DB_HOST.
- DB_PORT.
- DB_NAME.
- DB_USER.
- DB_PASSWORD.

### Relación con la arquitectura existente de EduBío 360

La decisión vigente del backend es **MySQL 8**. La configuración Oracle anterior queda retirada de esta implementación.

Para cumplir IE4 y mantener una ejecución reproducible:

- MySQL es la base relacional persistente.
- H2 se conserva únicamente como perfil rápido de pruebas y desarrollo.
- Docker Compose levanta MySQL y las bases lógicas de los seis microservicios.
- Cada microservicio conserva ownership de sus datos y no accede directamente a tablas de otro servicio.

Toda respuesta futura sobre la base de datos del backend debe asumir MySQL, salvo que una pauta distinta indique expresamente otra tecnología.

---

## 6. Postman y manejo de errores — IE3

El material docente define siete peticiones por servicio:

1. Listar -> 200.
2. Obtener por id -> 200.
3. Obtener inexistente -> 404.
4. Crear -> 201.
5. Crear inválido -> 400 con detalles.
6. Actualizar -> 200.
7. Eliminar -> 204.

La colección debe utilizar variables de URL por servicio.

Si se evalúan cinco microservicios de EduBío 360, esta estructura implica como referencia **35 casos**.
Si se evalúan seis, implica **42 casos**.
Si el docente exige siete, implica **49 casos**.

El número final depende del alcance de microservicios efectivamente presentado; el patrón de siete casos por servicio proviene del material docente.

### GlobalExceptionHandler

Cada servicio evaluado debe homogeneizar sus respuestas de error mediante una clase de configuración basada en @RestControllerAdvice y @ExceptionHandler para, como mínimo:

- MethodArgumentNotValidException.
- HttpMessageNotReadableException.
- Recurso inexistente, con 404.

---

## 7. Maven, empaquetado y ejecución — IE7

Debe demostrarse:

1. Ejecución de pruebas.
2. Verificación de cobertura.
3. Empaquetado.
4. Existencia del .jar.
5. Ejecución del artefacto.

Comandos de referencia:

    mvn test
    mvn clean verify
    mvn clean package
    java -jar target/<servicio>.jar

El artefacto debe ser un fat jar ejecutable generado mediante Spring Boot Maven Plugin.

---

## 8. Docker Compose y ejecución reproducible — IE4 / IE10

El material docente recomienda levantar el sistema completo con:

    docker compose up --build

El Compose debe levantar MySQL y los microservicios incluidos en la entrega usando el perfil mysql.

También debe ser posible ejecutar un servicio individual con el perfil H2 para una comprobación rápida.

La documentación debe incluir:

- requisitos;
- versión de Java;
- versión de Maven;
- Docker / Docker Compose;
- puertos;
- endpoints;
- perfiles de base de datos;
- variables de entorno;
- comandos de build;
- comandos de pruebas;
- Swagger/OpenAPI;
- Postman;
- empaquetado .jar.

---

## 9. Git y trazabilidad — IE9

La pauta exige evidencia real de versionado.

Ramas:

- main.
- develop.
- feature/*.

Los cambios deben realizarse mediante ramas de funcionalidad y commits descriptivos. El repositorio ya adopta Git Flow, por lo que las nuevas tareas de EP02 deben continuar ese flujo y no borrar el historial previo.

Convención actual del repositorio:

- feat: nueva funcionalidad.
- fix: corrección.
- docs: documentación.
- chore: configuración o mantenimiento.

---

## 10. Calidad adicional

El material del docente identifica calidad adicional que fortalece IE2, IE8 e IE9:

### Pruebas automatizadas

- Unitarias con JUnit + Mockito.
- Controladores con MockMvc.
- Verificación de respuestas 200 / 201 / 204 / 400 / 404.
- BDD con Cucumber por servicio.

### Cobertura JaCoCo

- Umbral mostrado: **100% LINE**.
- Verificación mediante mvn verify.
- El ejemplo excluye entidades y configuración consideradas boilerplate.

La cobertura no debe maquillarse excluyendo lógica real de negocio.

### Documentación por servicio

- OpenAPI.
- Swagger UI.
- ReDoc.
- Página de presentación por servicio.

Rutas de referencia del material:

- /.
- /swagger-ui/index.html.
- /v3/api-docs.yaml.

---

## 11. Guion de video exigido

El material propone una presentación de 3 a 8 minutos con el siguiente hilo:

1. **Presentación**: integrantes, caso y arquitectura.
2. **Clonar y ejecutar**: git clone + docker compose up --build y mostrar servicios levantados.
3. **Postman**: mostrar casos CRUD correctos y errores 400/404.
4. **Base de datos**: mostrar application-mysql.yml, driver en pom.xml y persistencia en MySQL.
5. **Maven/JAR**: mvn clean package, mostrar target/*.jar y ejecutar el artefacto.
6. **Cierre**: relacionar evidencia con los indicadores de la rúbrica.

Audio:

- usar micrófono adecuado;
- grabar en lugar silencioso;
- evitar música que compita con la voz;
- mantener volumen y modulación estables.

---

## 12. Checklist final de cumplimiento

Antes de declarar EP02 terminada deben existir evidencias de:

- [ ] IE1 endpoints RESTful funcionales, CRUD, validación y OpenAPI.
- [ ] IE2 capas controller/service/repository/model + config.
- [ ] IE3 colección Postman con casos felices y de error + @ControllerAdvice.
- [ ] IE4 perfil MySQL + driver + Docker Compose.
- [ ] IE5 CRUD JPA realmente persistente.
- [ ] IE6 relaciones @OneToMany / @ManyToOne en los servicios evaluados conforme al dominio.
- [ ] IE7 mvn clean package genera .jar ejecutable.
- [ ] IE8 pom.xml con dependencias y plugins requeridos.
- [ ] IE9 Git con main/develop/feature/* y commits descriptivos.
- [ ] IE10 README + Docker Compose + instrucciones reproducibles.
- [ ] IE11 video técnico ordenado de 3 a 8 minutos.
- [ ] IE12 audio claro.
- [ ] Pruebas JUnit + Mockito.
- [ ] Pruebas MockMvc.
- [ ] Cucumber por servicio cuando se aplique la calidad adicional del material.
- [ ] JaCoCo verificado en mvn verify.
- [ ] Swagger/OpenAPI accesible.
- [ ] ReDoc/página de presentación por servicio si se incorpora la calidad adicional.

---

## 13. Regla de decisión para EduBío 360

A partir de este documento, cualquier pregunta como:

- "¿qué falta?";
- "¿esto cumple?";
- "¿qué debemos implementar?";
- "¿qué mostramos en el video?";
- "¿qué dependencia va en pom.xml?";
- "¿cuántos servicios deben estar listos?";
- "¿qué prueba debemos hacer?";

debe contestarse siguiendo este orden:

1. **Pauta EP02 e IE correspondiente.**
2. **Estado real del repositorio EduBío 360.**
3. **Adaptación al dominio que ya existe.**
4. **Evidencia necesaria para demostrar cumplimiento.**

No se debe sustituir una decisión existente del proyecto salvo que la pauta o una decisión posterior explícita del equipo la modifique. Para esta EP02, la decisión posterior ya está tomada: **MySQL reemplaza a Oracle en el backend**.

---

## 14. Fuente de consolidación

Este Markdown fue consolidado a partir de las capturas del material docente compartidas para la EP02, incluyendo:

- diapositiva "Evaluación Parcial N°2 — Qué deben entregar";
- "Cumplimiento de la Rúbrica — Evaluación Parcial N°2 (JVY0101)";
- README del caso TicketWave EP02;
- documento "Paso a Paso EP02";
- checklist final por indicador.

Los elementos propios de TicketWave se consideran **ejemplos de implementación**. Los Indicadores de Evaluación, sus pesos y las evidencias descritas se consideran la base de cumplimiento para adaptar EduBío 360.
