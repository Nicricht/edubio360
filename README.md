# EduBío 360 - Microservicios (Fase 1)

Base funcional mínima de la arquitectura de EduBío 360. El objetivo es demostrar la separación de servicios, Gateway, Eureka, comunicación REST, Circuit Breaker y mensajería asíncrona. con Java 21, Spring Boot 3, Spring Cloud Gateway, Eureka, RabbitMQ y servicios separados por microservicios.

## GIT FLOW 

Elegí el flujo de trabajo "Git flow" porque me permite mantener el desarrollo del proyecto más ordenado y separar los distintos tipos de cambios que voy desarrollando en ramas propias llamadas feature, lo cual da más seguridad cuando uno va modificando código, incluso es más cómodo cuando surge problemas en la rama principal, debido a que uno puede resolverlo en la rama llamada hotfix sin afectar el resto del código, además cuando uno trabaja en equipo, en este caso trabajé solo, la mejor forma de revisar el código de un integrante es cuando este realiza un pull request, asegurandose de que no haya conflictos.

### Tipos de ramas:
main: Es la rama principal la cual será llevada a producción.
develop: Rama que después de un pull request obtiene los cambios realizados en feature.
feature: Donde se desarrolla nuevas funcionalidades
hotfix: Correcciones urgentes en producción.

### Nombramiento de ramas:

-feature/(nombre): Escribir una funcionalidad, Se crean a partir de develop y se unen a develop gracias a pull request.

-hotfix/(nombre): Se crean desde main y se integran allí a ravés de pull request.

### Nombramiento de commmits:
tipo(lugar de la acción):detalles

feat: nueva funcionalidad.
fix: Solucionar un problema.
docs: documentación.
chore: mantenimiento o configuración.

### Revisión de cambios

Antes de integrar cambios se revisa el diff y se comprueba que el proyecto compile correctamente.

Las features se integran a develop mediante Pull Request como mencioné anteriormente.

Los hotfix se integran a main mediante Pull Request y luego se sincronizan con develop como mencioné anteriorment.