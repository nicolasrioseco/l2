#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
@Registro
Feature: Registro

  @Highest
  Scenario: Registrar dni
    Given un nuevo usuario ingresa a la app y avanza al Login
    When oprime sobre el boton Crear mi cuenta
    And ingresa su DNI "16366559", nombre "Automation", apellido "Tecso", telefono "2494281960" y presiona Siguiente
    And ingresa su nombre de usuario "10TecsoAuto", contraseña "AutomationTecso1", repite su contraseña "AutomationTecso1", su mail "nicolas.rioseco@tecso.coop", repite su mail "nicolas.rioseco@tecso.coop", acepta T&C y presiona sobre Crear Cuenta
    Then se crea correctamente el usuario

  @Highest
  Scenario: Recuperar Usuario
    Given un nuevo usuario ingresa a la app y avanza al Login
    When oprime sobre el boton Olvide mi usuario
    And ingresa su Numero de Docuemento "27179085769" su Email "jorgelinagschwind@gmail.com" y presiona Enviar
    Then se crea el usuario recibe un mail con su Nombre de Usuario

  @High
  Scenario: Logearse
    Given un nuevo usuario ingresa a la app y avanza al Login
    When ingresa usuario "jp85"
    And ingresa contraseña "anto1234"
    And presiona sobre el boton Ingresar
    Then el usuario queda logeado
    
   @Denuncia
   Scenario: Denuncia de siniestro - Tipo Robo - Bien asegurado: Joya
   	Given un nuevo usuario ingresa a la app y avanza al Login
    When ingresa usuario "jp85"
    And ingresa contraseña "anto1234"
    And presiona sobre el boton Ingresar
    When presiona sobre el boton Denunciar un Siniestro
    And selecciona "Joya" como Bien Asegurado
    When selecciona "Accidente" como Tipo de Siniestro
    And completa fecha y hora del siniestro y la descripcion "Perdió la joya"
    And presiona el boton Siguiente
    When selecciona la provincia "Cordoba", completa el codigo postal "CORDOBA-CBA", completa la calle "Benancio Flores", luego completa el numero "1234", completa el piso "2", completa el departamento "C" y completa la unidad "2"
    And presiona el boton Siguiente
    
    
    
    