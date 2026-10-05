Feature: Relación académica
  Scenario: agregar una oferta a una sede
    Given una sede académica
    When agrego una oferta académica
    Then la sede contiene 1 oferta
