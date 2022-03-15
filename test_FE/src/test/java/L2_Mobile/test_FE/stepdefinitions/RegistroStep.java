package L2_Mobile.test_FE.stepdefinitions;

import org.springframework.beans.factory.annotation.Autowired;

import L2_Mobile.test_FE.pages.Landing;
import L2_Mobile.test_FE.pages.Login;
import L2_Mobile.test_FE.pages.Registro;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistroStep extends StepBasico{
	
	@Autowired
	Registro paginaRegistro;
	@Autowired
	Login paginaLogin;
	@Autowired
	Landing paginaLanding;
	
	public static String nombre;
		
	@When("oprime sobre el boton Crear mi cuenta")
	public void oprime_sobre_el_boton_Crear_mi_cuenta() throws Exception {
		try {
			paginaRegistro.clickBotonCrearMiCuenta();
			paginaRegistro.validarMensajeAlIngresarAlRegistro();	
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}
	
	@When("ingresa su DNI {string}, nombre {string}, apellido {string}, telefono {string} y presiona Siguiente")
	public void ingresa_su_DNI_nombre_apellido_y_telefono_y_presiona_Siguiente(String dni, String nombreUsuario, String apellido, String telefono) throws Exception {
		try {
			
			RegistroStep.nombre = nombreUsuario;
			paginaRegistro.ingresarDni(dni);
			paginaRegistro.ingresarNombre(nombre);
			paginaRegistro.ingresarApellido(apellido);
			paginaRegistro.ingresarTelefono(telefono);
			paginaRegistro.clickBotonSiguiente();
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	  
	}
	
	@When("ingresa su nombre de usuario {string}, contraseña {string}, repite su contraseña {string}, su mail {string}, repite su mail {string}, acepta T&C y presiona sobre Crear Cuenta")
	public void ingresa_su_nombre_de_usuario_contraseña_repite_su_contraseña_su_mail_repite_su_mail_acepta_T_C_y_presiona_sobre_Crear_Cuenta(String nombreUsuario, String contrasena, String repContrasena, String mail, String repMail) throws Exception {
		try {
			paginaRegistro.validarMensajeAvanzarEnRegistro();
			paginaRegistro.ingresarNombreUsuario(nombreUsuario);
			paginaRegistro.ingresarContrasena(contrasena);
			paginaRegistro.repetirContrasena(repContrasena);
			paginaRegistro.ingresarMail(mail);
			paginaRegistro.repetirMail(repMail);
			paginaRegistro.aceptaTC();
			paginaRegistro.clickBotonCrearCuenta();
			
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	  
	}

	@Then("se crea correctamente el usuario")
	public void se_crea_correctamente_el_usuario() throws Exception {
		try {
			paginaRegistro.validarMensajeCreacionUsuario();
			paginaRegistro.clickIngresar();
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}
	
}
