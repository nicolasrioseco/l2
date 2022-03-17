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
	private static final By btn_Accidente = By.xpath("//android.view.View[@content-desc='Accidente']");
	private static final By btn_Sustraccion = By.xpath("//android.view.View[@content-desc='Sustracción']");
	private static final By inp_Fecha = By.xpath("//android.view.View[contains(@content-desc, 'dd/mm/aaaa')]");
	private static final String value_Fecha = "//android.view.View[@content-desc='";
	private static final By inp_Hora = By.xpath("//android.view.View[@content-desc='00:00 00:00'");
	private static final By set_Hora = By.xpath("//android.widget.Button[@content-desc='ACEPTAR']");
	private static final By inp_Descripcion = By.xpath("//android.widget.EditText[@text, 'Escribí más detalles acá']");
	private static final By btn_Siguiente = By.xpath("//android.widget.Button[@content-desc='Siguiente']");
	private static final By btn_Provincia = By.xpath("//android.view.View[6]/android.view.View");
	private static final String value_Provincia = "//android.view.View[@content-desc='";
	private static final By inp_CP = By.xpath("//android.widget.EditText[1]");
	private static final By inp_Calle = By.xpath("//android.widget.EditText[2]");
	private static final By inp_Numero = By.xpath("//android.widget.EditText[3]");
	private static final By inp_Piso = By.xpath("//android.widget.EditText[4]");
	private static final By inp_Dpto = By.xpath("//android.widget.EditText[5]");
	private static final By inp_Unidad = By.xpath("//android.widget.EditText[6]");
	
	
	public void clickBotonDenunciar() throws Exception {
		clickElemento(btn_Denunciar, "Boton Denunciar Siniestro");
	}

	public void validarPantallaDenuncia() throws Exception {
		Thread.sleep(1000);
		validarContentDescDeElemento(txt_Page_Denunciar, "Denunciar siniestro", "Titulo de la seccion");
	}
	
	public void clickBienAsegurado(String bienAsegurado) throws Exception {
		Thread.sleep(1000);
		if(bienAsegurado.toUpperCase().contains("JOYA")) {
			System.out.println("Haciendo click en Joya");
			clickElemento(btn_bienJoya, "Boton Seleccionar Joya Como Bien Asegurado");
		}else if(bienAsegurado.toUpperCase().contains("Vehiculo")) {
			clickElemento(btn_bienVehiculo, "Boton Seleccionar Vehiculo Como Bien Asegurado");
		}else if(bienAsegurado.toUpperCase().contains("Inmueble")) {
			clickElemento(btn_bienInmueble, "Boton Seleccionar Inmueble Como Bien Asegurado");
		}else if(bienAsegurado.toUpperCase().contains("Tecnologia")) {
			clickElemento(btn_bienTecno, "Boton Seleccionar Tecno Como Bien Asegurado");
		}else {
			System.out.println("El bien asegurado no existe");
		}
	}
	
	public void clickTipoSiniestro(String tipoSiniestro) throws Exception {
		
		if(tipoSiniestro.toUpperCase().contains("ACCIDENTE")) {
			clickElemento(btn_Accidente, "Boton Seleccionar Accidente Como Tipo de Siniestro");
		}else if(tipoSiniestro.toUpperCase().contains("SUSTRACCION")) {
			clickElemento(btn_Sustraccion, "Boton Seleccionar Sustraccion Como Tipo de Siniestro");
		}else {
			System.out.println("El tipo de siniestro no existe");
		}
	}

	public void completaFecha() throws Exception {
		
		clickElemento(inp_Fecha, "Campo para ingresar la fecha del siniestro");
		String fecha = obtenerFecha();
		System.out.println("Fecha: " + fecha);
		By set_Fecha = By.xpath(value_Fecha + fecha + "']");
		clickElemento(set_Fecha,"Boton para seleccionar la fecha siniestro");
		clickElemento(inp_Hora, "Campo para ingresar la hora del siniestro");
		clickElemento(set_Hora, "Boton para seleccionar la hora del siniestro");
	}

	public void ingresaDescripcion(String descripcion) throws Exception {
		escribirTextoConTecladoMobile(inp_Descripcion, descripcion, "Campo para ingresar la descripcion");
	}

	public void clickSiguiente() throws Exception {
		
		clickElemento(btn_Siguiente, "Boton Siguiente");
	}

	public void completaStep2(String provincia, String cp, String calle, String numero, String piso, String dpto,
			String unidad) throws Exception {
		clickElemento(btn_Provincia, "Campo para seleccionar la provincia");
		By set_Provincia = By.xpath(value_Provincia + provincia.toUpperCase() + "']");
		clickElemento(set_Provincia, "Seleccion de provincia");
		escribirTextoConTecladoMobile(inp_CP, cp, "Campo para ingresar el codigo postal");
		escribirTextoConTecladoMobile(inp_Calle, calle, "Campo para ingresar la calle");
		escribirTextoConTecladoMobile(inp_Numero, numero, "Campo para ingresar el numero");
		escribirTextoConTecladoMobile(inp_Piso, piso, "Campo para ingresar el piso");
		escribirTextoConTecladoMobile(inp_Dpto, dpto, "Campo para ingresar el dpto");
		escribirTextoConTecladoMobile(inp_Unidad, unidad, "Campo para ingresar el unidad");
	}


}
