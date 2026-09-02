## Modelo de Ramificación

<<<<<<< HEAD
GitFlow: Elegí esta metodología para trabajar debido a que me permite organizar mejor las versiones del trabajo a parte de protger más el código en su rama main, de tal forma que no se trabaje directamente sobre esta, ya que yo y quizás otra persona estemos desarrollando código en sus propias ramas feature provenientes de develop facilitando la revisión de cambios a través de pull request y corregir problemas de una manera más ordenada.

### Tipos de Ramas en GitFlow
-Main: Es la rama principal la que será llevado a producción
-Develop: Es la rama que esta después de main, de aquí aparecen las ramas feature.
-Feature/(algo): Ramas para desarrollar,documentar, arreglar..etc, se integran a feature a través de un pull request.
-Hotfix/algo: Se integran a main
=======
Base funcional mínima de la arquitectura de EduBío 360. El objetivo es demostrar la separación de servicios, Gateway, Eureka, comunicación REST, Circuit Breaker y mensajería asíncrona. con Java 21, Spring Boot 3, Spring Cloud Gateway, Eureka, RabbitMQ y servicios separados por microservicios.

## GIT FLOW 

Elegí el flujo de trabajo "Git flow" porque me permite mantener el desarrollo del proyecto más ordenado y separar los distintos tipos de cambios que voy desarrollando en ramas propias llamadas feature, lo cual da más seguridad cuando uno va modificando código, incluso es más cómodo cuando surge problemas en la rama principal, debido a que uno puede resolverlo en la rama llamada hotfix sin afectar el resto del código, además cuando uno trabaja en equipo, en este caso trabajé solo, la mejor forma de revisar el código de un integrante es cuando este realiza un pull request, asegurandose de que no haya conflictos.

### Tipos de ramas:
main: Es la rama principal la cual será llevada a producción.
develop: Rama que después de un pull request obtiene los cambios realizados en feature.
feature: Donde se desarrolla nuevas funcionalidades
hotfix: Correcciones urgentes en producción.

### Nombramiento de ramas:

-feature/(nombre)
-hotfix/(nombre)



>>>>>>> 1c9aa8c028092d29a778657eec03ed3e3cc1a377
