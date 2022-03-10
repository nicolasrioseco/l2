package L2_Mobile.test_FE.stepdefinitions;

import org.springframework.beans.factory.annotation.Autowired;
import L2_Mobile.test_FE.pages.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep extends StepBasico{
	
	@Autowired
	private Login paginaLogin;
	
	@Given("un nuevo usuario ingresa a la app y avanza al Login")
	public void un__nuevo_usuario_ingresa_a_la_app_y_avanza_al_Login() throws Exception {
		
		try {
			paginaLogin.recorrerOnBoarding();
			paginaLogin.validarPantallaBienvenida();		
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}
	
	@When("ingresa usuario {string}")
	public void ingresa_usuario(String email) throws Exception {
		try {
			paginaLogin.ingresarUsuario(email);
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}

	@When("ingresa contraseña {string}")
	public void ingresa_contraseña(String password) throws Exception {
		try {
			paginaLogin.ingresarContraseña(password);
		} catch (Exception e) {
			manejarError(e);	
			throw e;
		}
	}
	
	@When("presiona sobre el boton Ingresar")
	public void presiona_sobre_el_boton_Ingresar() {
		try {
			paginaLogin.clickBotonIngresar();
		} catch (Exception e) {
			manejarError(e);
		}
	}

	@Then("el usuario queda logeado")
	public void el_usuario_queda_logeado()  {
		try {
			paginaLogin.clickBotonIngresar();
			paginaLogin.validarPantallaLogin();
		} catch (Exception e) {
			manejarError(e);
		}
	}
	
}
