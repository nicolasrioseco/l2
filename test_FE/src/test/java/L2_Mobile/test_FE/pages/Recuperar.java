package L2_Mobile.test_FE.pages;


import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import L2_Mobile.test_FE.util.enums.BotonCelular;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Component
@Scope("cucumber-glue")
public class Recuperar extends AppiumBase {
	
	@Autowired
	public Recuperar(AndroidDriver<MobileElement> driver) {
		super(driver);
	}
	
	private static final By botonOlvideMiUsuario = By.xpath("//android.view.View[@content-desc='Olvidé mi usuario']");
	private static final By textoPantallaRecuperarUsuario = By.xpath("//android.view.View[@content-desc='¿Olvidaste tu usuario?']");
	private static final By labelIngresarNumDoc = By.xpath("//android.widget.EditText[1]");
	private static final By labelIngresarEmail = By.xpath("//android.widget.EditText[2]");
	private static final By botonEnviar = By.xpath("//android.widget.Button[@content-desc='Enviar']");
	private static final By textoConfirmacionAlta = By.xpath("//android.view.View[@content-desc='Usuario creado con éxito']");
	
	
	public void clickBotonRecuperarUsuario() throws Exception {
		clickElemento(botonOlvideMiUsuario, "Boton Olvidé mi usuario");
	}
	
	public void validarMensajeAlIngresarARecuperarUsuario() throws Exception {
		validarContentDescDeElemento(textoPantallaRecuperarUsuario, "¿Olvidaste tu usuario?", "Pantalla de Recupero de Usuario");
	}
	
	public void ingresarNumDoc(String dni) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarNumDoc, dni, "Número de Documento");
	}
	
	public void ingresarEmail(String nombre) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarEmail, nombre, "Email");
	}
		
	public void clickBotonEnviar() throws Exception {
		clickElemento(botonEnviar, "Boton Enviar");
	}
		
	
	public void validarMensajeCreacionUsuario() throws Exception {
		validarContentDescDeElemento(textoConfirmacionAlta, "Usuario creado con éxito", "Pantalla de confirmación de Alta de Usuario");
	}
	
	public void volverAlLogin() throws Exception {
		presionarBotonMobile(BotonCelular.ATRAS);
	}
}
