package L2_Mobile.test_FE.stepdefinitions;

import org.springframework.beans.factory.annotation.Autowired;

import L2_Mobile.test_FE.pages.Landing;
import L2_Mobile.test_FE.pages.Login;
import L2_Mobile.test_FE.pages.Recuperar;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RecuperarStep extends StepBasico{
	
	@Autowired
	Recuperar paginaRecuperar;
	@Autowired
	Login paginaLogin;
	@Autowired
	Landing paginaLanding;
		
	@When("oprime sobre el boton Olvide mi usuario")
	public void oprime_sobre_el_boton_Olvide_mi_usuario() throws Exception {
		try {
			paginaRecuperar.clickBotonRecuperarUsuario();
			paginaRecuperar.validarMensajeAlIngresarARecuperarUsuario();	
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}

	@When("ingresa su Numero de Docuemento {string} su Email {string} y presiona Enviar")
	public void ingresa_su_Numero_de_Docuemento_su_Email_y_presiona_Enviar(String numDoc, String mail) throws Exception {
		try {
			paginaRecuperar.ingresarNumDoc(numDoc);
			paginaRecuperar.ingresarEmail(mail);
			paginaRecuperar.clickBotonEnviar();
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}

	@Then("se crea el usuario recibe un mail con su Nombre de Usuario")
	public void se_crea_el_usuario_recibe_un_mail_con_su_Nombre_de_Usuario() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(5000);
	    throw new io.cucumber.java.PendingException();
	}
	
}
