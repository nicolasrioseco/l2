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
	public void presiona_sobre_el_boton_Denunciar_un_Siniestro() {
		try {
			paginaDenuncia.clickBotonDenunciar();
			paginaDenuncia.validarPantallaDenuncia();	
		} catch (Exception e) {
			manejarError(e);
		}
	}

	@When("selecciona {string} como Bien Asegurado")
	public void selecciona_como_Bien_Asegurado(String bienAsegurado) {
		try {
			paginaDenuncia.clickBienAsegurado(bienAsegurado);
		} catch (Exception e) {
			manejarError(e);
		}
	}
	
	@When("selecciona {string} como Tipo de Siniestro")
	public void selecciona_como_Tipo_de_Siniestro(String tipoSiniestro) {
		try {
			paginaDenuncia.clickTipoSiniestro(tipoSiniestro);
		} catch (Exception e) {
			manejarError(e);
		}
	}

	@When("completa fecha y hora del siniestro y la descripcion {string}")
	public void completa_fecha_y_hora_del_siniestro_y_la_descripcion(String descripcion) {
		try {
			paginaDenuncia.completaFecha();
			paginaDenuncia.ingresaDescripcion(descripcion);
		} catch (Exception e) {
			manejarError(e);
		}
	}
	
	@When("presiona el boton Siguiente")
	public void presiona_boton_Siguiente() {
		try {
		paginaDenuncia.clickSiguiente();
		} catch (Exception e) {
			manejarError(e);
		}
	}
	
	@When("selecciona la provincia {string}, completa el codigo postal {string}, completa la calle {string}, luego completa el numero {string}, completa el piso {string}, completa el departamento {string} y completa la unidad {string}")
	public void selecciona_la_provincia_completa_el_codigo_postal_completa_la_calle_luego_completa_el_numero_completa_el_piso_completa_el_departamento_y_completa_la_unidad(String provincia, String cp, String calle, String numero, String piso, String dpto, String unidad) {

		try{
			paginaDenuncia.completaStep2(provincia,cp,calle,numero,piso,dpto,unidad);
		}catch (Exception e) {
			manejarError(e);
		}
	}
	

}
