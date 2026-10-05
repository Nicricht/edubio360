Feature: Solicitud de orientación
  Scenario: crear solicitud pendiente
    Given una nueva solicitud de orientación
    Then su estado es "PENDIENTE"
