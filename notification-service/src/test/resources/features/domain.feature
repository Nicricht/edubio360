Feature: Envíos de notificación
  Scenario: registrar un envío
    Given una notificación
    When agrego un envío
    Then la notificación contiene 1 envío
