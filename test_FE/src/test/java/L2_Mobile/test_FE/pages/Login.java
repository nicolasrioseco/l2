package L2_Mobile.test_FE.pages;


import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Component
@Scope("cucumber-glue")
public class Login extends AppiumBase {
	
	@Autowired
	public Login(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	private static final By step1OnBoarding = By.xpath("//android.widget.ImageView[@index='0']");
	private static final By botonSigOnBoarding = By.xpath("//android.widget.Button[2]");
	private static final By textPantallaIngresoAPP = By.xpath("//android.view.View[@content-desc='¡Te damos la bienvenida!']");
	private static final By textContraseña = By.xpath("//android.widget.EditText[2]");
	private static final By botonIngresar = By.xpath("//android.widget.Button[@content-desc='Ingresar']");
	private static final By textUsuario = By.xpath("//android.widget.EditText[1]");
	private static final By iconoSalir = By.xpath("(//android.widget.Button)[1]");
	private static final By textPantallaLogin = By.xpath("//android.view.View[@content-desc='¡Te damos la bienvenida!']");
	
	public void recorrerOnBoarding() throws Exception {
		List<String> stepOnBoarding = Arrays.asList("Autogestión", "Consultas", "Siniestros");
		for(int i = 0; i< stepOnBoarding.size();i++) {
			String step = (String) stepOnBoarding.get(i);
			System.out.println("Estamos en el step: " + i + " del Onboarding");
			validarPantallaOnBoarding(step);
			clickElemento(botonSigOnBoarding, "Siguiente Step");
		}
	}
	
	public void validarPantallaOnBoarding(String step) throws Exception {
			Thread.sleep(1000);
			validarContentDescDeElemento(step1OnBoarding, step, "Texto del Step OnBoarding");
	}

	
	public void validarPantallaBienvenida() throws Exception {
		validarContentDescDeElemento(textPantallaIngresoAPP, "¡Te damos la bienvenida!", "Texto bienvenida login");		
	}
	
	public void validarPantallaLogin() throws Exception {
		validarContentDescDeElemento(textPantallaLogin, "¡Te damos la bienvenida!", "Texto bienvenida login");		
	}
	
	public void ingresarUsuario(String usuario) throws Exception{
		escribirTextoEnElementoConTecladoMobile(textUsuario, usuario, "Usuario");
	}
	
	public void ingresarContraseña(String contraseña) throws Exception{
		escribirTextoEnElementoConTecladoMobile(textContraseña, contraseña, "Contraseña");
	}
	
	public void clickBotonIngresar() throws Exception{
		clickElemento(botonIngresar, "Ingresar");
	}
	
	public void cerrarSesion() throws Exception {
		esperarMilisegundos(1000);
		clickElemento(iconoSalir, "Icono Salir");
	}
}
