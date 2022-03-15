package L2_Mobile.test_FE.stepdefinitions;

import org.springframework.beans.factory.annotation.Autowired;

import L2_Mobile.test_FE.pages.Denuncia;
import L2_Mobile.test_FE.pages.Landing;
import L2_Mobile.test_FE.pages.Login;
import io.cucumber.java.en.When;

public class DenunciaStep extends StepBasico{

	@Autowired
	Denuncia paginaDenuncia;
	@Autowired
	Login paginaLogin;
	@Autowired
	Landing paginaLanding;

	public static String nombre;

	@When("presiona sobre el boton Denunciar un Siniestro")
	public void presiona_sobre_el_boton_Denunciar_un_Siniestro() throws Exception {
		try {
			paginaDenuncia.clickBotonDenunciar();
			paginaDenuncia.validarPantallaDenuncia();	
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}

	@When("selecciona {string} como Bien Asegurado")
	public void selecciona_como_Bien_Asegurado(String bienAsegurado) throws Exception {
		try {
			paginaDenuncia.clickBienAsegurado(bienAsegurado);
			paginaDenuncia.validarPantallaDenuncia();	
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}
	
	@When("permite grabar audio")
	public void permite_grabar_audio() throws Exception {
		try {
			paginaDenuncia.clickGrabarAudio();
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}
	
	@When("selecciona {string} como Tipo de Siniestro")
	public void selecciona_como_Tipo_de_Siniestro(String tipoSiniestro) throws Exception {
		try {
			paginaDenuncia.clickBienAsegurado(tipoSiniestro);
			paginaDenuncia.validarPantallaDenuncia();	
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}


}
