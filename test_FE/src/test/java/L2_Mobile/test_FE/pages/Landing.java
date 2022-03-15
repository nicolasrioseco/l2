package L2_Mobile.test_FE.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import L2_Mobile.test_FE.util.enums.Selector;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Component
@Scope("cucumber-glue")
public class Landing extends AppiumBase {
	
	@Autowired
	public Landing(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	private static final String menuMiART = "Inicio";
	private static final String menuSiniestros = "Siniestros";
	private static final String menuAgenda = "Agenda";
	private static final String menuFormacion = "Formación";
	private static final String mensajeBienvenida = "Beneficiario";

	public void validarTextoDeIngreso() throws Exception {
		//esperarElemento(mensajeBienvenida, 60);
		validarQueElementoContengaUnTexto(mensajeBienvenida, "BENEFICIARIO", "Mensaje de Bienvenida", Selector.contieneTexto);
	}

	public void presionarMIART() throws Exception {
		clickElemento(menuMiART, "Menu ART", Selector.contieneTexto);
	}
	
	public void presionarAgenda() throws Exception {
		clickElemento(menuAgenda, "Menu Agenda", Selector.contieneTexto);
	}
	
	public void presionarSiniestros() throws Exception {
		esperarMilisegundos(2000);
		clickElemento(menuSiniestros, "Menu Siniestros", Selector.contieneTexto);
	}
	
	public void presionarFormacion() throws Exception {
		clickElemento(menuFormacion, "Menu Formacion", Selector.contieneTexto);
	}
}
