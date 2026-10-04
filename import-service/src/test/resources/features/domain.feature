Feature: Errores de importación
  Scenario: registrar un error
    Given una importación
    When agrego un error de importación
    Then la importación contiene 1 error
