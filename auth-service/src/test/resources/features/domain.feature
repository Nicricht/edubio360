Feature: Roles de usuario
  Scenario: crear usuario con rol
    Given un usuario estudiante
    Then el rol principal es "STUDENT"
