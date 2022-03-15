package L2_Mobile.test_FE.pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Component
@Scope("cucumber-glue")
public class Denuncia extends AppiumBase {
	
	@Autowired
	public Denuncia(AndroidDriver<MobileElement> driver) {
		super(driver);
	}
	
	private static final By btn_Denunciar = By.xpath("//android.view.View[@content-desc='Denunciar un siniestro']");
	private static final By txt_Page_Denunciar = By.xpath("//android.view.View[@content-desc='Denunciar siniestro']");
	private static final By btn_bienJoya = By.xpath("//android.widget.ImageView[contains(@content-desc,'JOYAS')]");
	private static final By btn_bienVehiculo = By.xpath("//android.widget.ImageView[contains(@content-desc,'PEUGEOT')]");
	private static final By btn_bienInmueble = By.xpath("//android.widget.ImageView[contains(@content-desc,'BIANCARDI')]");
	private static final By btn_bienTecno = By.xpath("//android.widget.ImageView[contains(@content-desc,'Tecno')]");
	private static final By btn_GrabarAudio = By.id("com.android.packageinstaller:id/permission_allow_button");
	private static final By btn_Accidente = By.xpath("//android.view.View[@content-desc='Accidente']");
	private static final By btn_Sustraccion = By.xpath("//android.view.View[@content-desc='Sustracción']");
	
	
	public void clickBotonDenunciar() throws Exception {
		clickElemento(btn_Denunciar, "Boton Denunciar Siniestro");
	}

	public void validarPantallaDenuncia() throws Exception {
		validarContentDescDeElemento(txt_Page_Denunciar, "Denunciar siniestro", "Titulo de la seccion");
	}
	
	public void clickBienAsegurado(String bienAsegurado) throws Exception {
		
		if(bienAsegurado == "Joya") {
			clickElemento(btn_bienJoya, "Boton Seleccionar Joya Como Bien Asegurado");
		}else if(bienAsegurado == "Vehiculo") {
			clickElemento(btn_bienVehiculo, "Boton Seleccionar Vehiculo Como Bien Asegurado");
		}else if(bienAsegurado == "Inmueble") {
			clickElemento(btn_bienInmueble, "Boton Seleccionar Inmueble Como Bien Asegurado");
		}else if(bienAsegurado == "Tecnologia") {
			clickElemento(btn_bienTecno, "Boton Seleccionar Tecno Como Bien Asegurado");
		}else {
			System.out.println("El bien asegurado no existe");
		}
	}

	public void clickGrabarAudio() throws Exception {
		clickElemento(btn_GrabarAudio, "Boton Grabar Audio");
		
	}
	
	public void clickTipoSiniestro(String tipoSiniestro) throws Exception {
		
		if(tipoSiniestro == "Accidente") {
			clickElemento(btn_Accidente, "Boton Seleccionar Accidente Como Tipo de Siniestro");
		}else if(tipoSiniestro == "Sustraccion") {
			clickElemento(btn_Sustraccion, "Boton Seleccionar Sustraccion Como Tipo de Siniestro");
		}else {
			System.out.println("El tipo de siniestro no existe");
		}
	}

}
