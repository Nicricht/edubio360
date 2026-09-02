## Modelo de Ramificación

GitFlow: Elegí esta metodología para trabajar debido a que me permite organizar mejor las versiones del trabajo a parte de protger más el código en su rama main, de tal forma que no se trabaje directamente sobre esta, ya que yo y quizás otra persona estemos desarrollando código en sus propias ramas feature provenientes de develop facilitando la revisión de cambios a través de pull request y corregir problemas de una manera más ordenada.

### Tipos de Ramas en GitFlow
-Main: Es la rama principal la que será llevado a producción
-Develop: Es la rama que esta después de main, de aquí aparecen las ramas feature.
-Feature/(algo): Ramas para desarrollar,documentar, arreglar..etc, se integran a feature a través de un pull request.
-Hotfix/algo: Se integran a main