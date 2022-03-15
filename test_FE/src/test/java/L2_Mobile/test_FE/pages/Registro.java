package L2_Mobile.test_FE.pages;


import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import L2_Mobile.test_FE.stepdefinitions.RegistroStep;
import L2_Mobile.test_FE.util.enums.BotonCelular;
import L2_Mobile.test_FE.util.enums.Selector;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Component
@Scope("cucumber-glue")
public class Registro extends AppiumBase {
	
	@Autowired
	public Registro(AndroidDriver<MobileElement> driver) {
		super(driver);
	}
	
	public static String textoAvanzarRegistro = "//android.view.View[@content-desc='";
	public static String textoAvanzarRegistro2 = ", ingresá estos últimos datos para crear tu cuenta.";
	
	private static final By botonCrearMiCuenta = By.xpath("//android.view.View[@content-desc='Crear mi cuenta']");
	private static final By textoIngresarRegistro = By.xpath("//android.view.View[@content-desc='¡Hola!']");
	private static final By botonSiguiente = By.xpath("//android.widget.Button[@content-desc='Siguiente']");
	private static final By labelIngresoDni = By.xpath("//android.widget.EditText[1]");
	private static final By labelIngresarNombre = By.xpath("//android.widget.EditText[2]");
	private static final By labelIngresarApellido = By.xpath("//android.widget.EditText[3]");
	private static final By labelIngresarTelefono = By.xpath("//android.widget.EditText[4]");
	private static final By labelIngresarNombreUsuario = By.xpath("//android.widget.EditText[1]");
	private static final By labelIngresarContrasena = By.xpath("//android.widget.EditText[2]");
	private static final By labelRepetirContrasena = By.xpath("//android.widget.EditText[3]");
	private static final By labelIngresarMail = By.xpath("//android.widget.EditText[4]");
	private static final By labelRepetirMail = By.xpath("//android.widget.EditText[@index='6']");
	private static final By checkTC = By.xpath("//android.widget.CheckBox");
	private static final By botonCrearCuenta = By.xpath("//android.widget.Button[@content-desc='Crear cuenta']");
	private static final By textoConfirmacionAlta = By.xpath("//android.view.View[@content-desc='Cuenta creada con éxito']");
	private static final By botonIngresar = By.xpath("//android.widget.Button[@content-desc='Ingresar']");
	
	
	public void clickBotonCrearMiCuenta() throws Exception {
		clickElemento(botonCrearMiCuenta, "Boton Crear Mi Cuenta");
	}
	
	public void validarMensajeAlIngresarAlRegistro() throws Exception {
		validarContentDescDeElemento(textoIngresarRegistro, "¡Hola!", "Pantalla de Registro");
	}
	
	public void ingresarDni(String dni) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresoDni, dni, "DNI");
	}
	
	public void ingresarNombre(String nombre) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarNombre, nombre, "Nombre");
	}
	
	public void ingresarApellido(String apellido) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarApellido, apellido, "Apellido");
	}
	
	public void ingresarTelefono(String telefono) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarTelefono, telefono, "Telefono");
	}
	
	public void clickBotonSiguiente() throws Exception {
		clickElemento(botonSiguiente, "Boton Siguiente");
	}
	
	public void clickIngresar() throws Exception {
		clickElemento(botonIngresar, "Boton Ingresar");
	}
	
	public void validarMensajeAvanzarEnRegistro() throws Exception {
		String validar = RegistroStep.nombre + textoAvanzarRegistro2;
		By textoValidarStepDos = By.xpath(textoAvanzarRegistro + RegistroStep.nombre + textoAvanzarRegistro2 + "']");
		validarContentDescDeElemento(textoValidarStepDos, validar, "Segunda Pantalla de Registro");
	}
	
	public void ingresarNombreUsuario(String nombreUsuario) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarNombreUsuario, nombreUsuario, "Nombre Usuario");
	}
	
	public void ingresarContrasena(String contrasena) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarContrasena, contrasena, "Contraseña");
	}
	
	public void repetirContrasena(String repContrasena) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelRepetirContrasena, repContrasena, "Repite Contraseña");
	}
	
	public void ingresarMail(String mail) throws Exception {
		escribirTextoEnElementoConTecladoMobile(labelIngresarMail, mail, "Mail");
	}
	
	public void repetirMail(String repMail) throws Exception {
		hacerScrollHaciaUnElemento("android.widget.CheckBox", Selector.className);
		escribirTextoConTecladoMobile(labelRepetirMail, repMail, "Repite Mail");
	}
	
	public void aceptaTC() throws Exception {
		clickElemento(checkTC, "Aceptar Términos y Condiciones");
	}
	
	public void clickBotonCrearCuenta() throws Exception {
		clickElemento(botonCrearCuenta, "Boton Crear Cuenta");
	}
	
	public void validarMensajeCreacionUsuario() throws Exception {
		validarContentDescDeElemento(textoConfirmacionAlta, "Cuenta creada con éxito", "Pantalla de confirmación de Alta de Usuario");
	}
	
	public void volverAlLogin() throws Exception {
		presionarBotonMobile(BotonCelular.ATRAS);
	}
}
