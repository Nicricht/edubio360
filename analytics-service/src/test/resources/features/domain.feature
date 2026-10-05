Feature: Puntos de métrica
  Scenario: agregar un punto
    Given una métrica
    When agrego un punto de métrica
    Then la métrica contiene 1 punto
