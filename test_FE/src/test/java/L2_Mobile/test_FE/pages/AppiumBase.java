package L2_Mobile.test_FE.pages;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import L2_Mobile.test_FE.util.enums.BotonCelular;
import L2_Mobile.test_FE.util.enums.Selector;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@Component
@Scope("cucumber-glue")
public class AppiumBase {
	
	protected static JavascriptExecutor js;
    protected Logger log = LoggerFactory.getLogger(AppiumBase.class);
	
	@Value("${demo}")
	private String demo; 

    @Value("${time.out}")
	private long timeOut;
	
	protected AndroidDriver<MobileElement> driver;
	
	@Autowired
	protected WebDriverWait wait;
   
	@Autowired
	 public AppiumBase(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
	}

	/*------------------------------------------------------------------------------------------------
    |  Método: encontrarElemento(String locator)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Busca un elemento dentro del DOM de la página según el xpath que le pasamos.
    |				Espera que el elemento esté visible hasta que se cumpla el tiempo que definimos para wait.
    |
    |  Parámetros:
    |	String locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
    |
    |  Retorna:
    |	WebElement -- Un elemento de la página con el cual interacturar.
    *------------------------------------------------------------------------------------------------*/
	private MobileElement encontrarElementoPorBy(By locator) throws Exception {
		if (demo.equals("true")) {esperarMilisegundos(1000);}
		return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	private MobileElement encontrarElementoPorTextoQueContiene(String textoDeElementoABuscar) throws Exception {
		if (demo.equals("true")) {esperarMilisegundos(1000);}
		return (MobileElement) wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+textoDeElementoABuscar+"\").instance(0);")));
    }

    private MobileElement encontrarElementoPorTexto(String textoDeElementoABuscar) throws Exception {
		if (demo.equals("true")) {esperarMilisegundos(1000);}
		return (MobileElement) wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+textoDeElementoABuscar+"\").instance(0);")));
    }
    
    private MobileElement encontrarElementoPorResourceID(String textoDeElementoABuscar) throws Exception {
		if (demo.equals("true")) {esperarMilisegundos(1000);}
		return (MobileElement) wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().resourceIdMatches(\""+textoDeElementoABuscar+"\").instance(0);")));
	}
	
	
	 /*------------------------------------------------------------------------------------------------
    |  Método: clickElemento(String locator)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Hace click sobre un elemento de la página que fue encontrado según el xpath que le pasamos.
    |				Antes de hacer click hace scroll hasta el elemento.
    |
    |  Parámetros:
    |	String locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
    |	String nombreElemento (Opcional) -- En el log se visualiza mas claramente el nombre del elemento y no el xpath
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
    public void clickElemento(String locator, String nombreElemento, Selector selector) throws Exception {
        if(selector == Selector.contieneTexto) encontrarElementoPorTextoQueContiene(locator).click();
        else if(selector == Selector.resourceId) encontrarElementoPorResourceID(locator).click();
        else if(selector == Selector.texto) encontrarElementoPorTexto(locator).click();
        else throw new Exception("El selector que se indicó no coincide con los esperados: contieneTexto o resourceId");
		log.info("Se hizo click en el elemento: "+nombreElemento);
	}
	
	public void clickElemento(By locator, String nombreElemento) throws Exception {
		encontrarElementoPorBy(locator).click();
		log.info("Se hizo click en el elemento: "+nombreElemento);
	}
	
	

	/*------------------------------------------------------------------------------------------------
    |  Método: escribirTextoEnElemento(String locator, String texto)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Escribe el texto que le pasamos dentro de un elemento de la página.
    |				Antes de hacer click hace scroll hasta el elemento.
    |
    |  Parámetros:
    |	String locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
    |	String texto   -- Texto a ingresar dentro del elemento. 
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
    public void escribirTextoEnElemento(String locator, String texto, Selector selector, String nombreElemento) throws Exception{
        if(selector == Selector.contieneTexto){
            encontrarElementoPorTextoQueContiene(locator).clear();
		    encontrarElementoPorTextoQueContiene(locator).sendKeys(texto);
        } else if(selector == Selector.resourceId) {
            encontrarElementoPorResourceID(locator).clear();
		    encontrarElementoPorResourceID(locator).sendKeys(texto);
        }
        else throw new Exception("El selector que se indicó no coincide con los esperados: contieneTexto o resourceId");
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
	}
	
	public void escribirTextoEnElemento(By locator, String texto, String nombreElemento) throws Exception{
		encontrarElementoPorBy(locator).clear();
		encontrarElementoPorBy(locator).sendKeys(texto);
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
	}
	
	public void escribirTextoConTecladoMobile(By locator, String texto, String nombreElemento) throws Exception{
		encontrarElementoPorBy(locator).click();
		Actions action = new Actions(driver);
		action.sendKeys(texto).perform();
		bajarTeclado();
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
    }
	
	
	/*------------------------------------------------------------------------------------------------
    |  Método: escribirTextoEnElementoConTecladoMobile(By locator, String texto)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Escribe el texto que le pasamos con el teclado del celular.
    |				Primero levanta el teclado del celular y escribe presionando las teclas del mismo
    |
    |  Parámetros:
    |	String locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
    |	String texto   -- Texto a ingresar dentro del elemento. 
    |	String nombreElemento (Opcional) -- En el log se visualiza mas claramente el nombre del elemento y no el xpath
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
    public void escribirTextoEnElementoConTecladoMobile(By locator, String texto, String nombreElemento) throws Exception{
		eliminarTextoDeElemento(locator);
		Actions action = new Actions(driver);
		action.sendKeys(texto).perform();
		bajarTeclado();
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
    }
    
    public void escribirTextoEnElementoConTecladoMobile(String locator, String texto, String nombreElemento, Selector selector) throws Exception{
        //eliminarTextoDeElemento(locator, selector);
        encontrarElementoPorResourceID(locator).click();
        Actions action = new Actions(driver);
        action.sendKeys(texto).perform();
        esperarMilisegundos(2000);
        //bajarTeclado();
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
	}

	/*------------------------------------------------------------------------------------------------
    |  Método: presionarBotonMobile(Teclas tecla)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Presiona un boton del celular
    |
    |  Parámetros:
    |	Teclas tecla   -- Boton a presionar. Proviene del ENUM Teclas.
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void presionarBotonMobile(BotonCelular tecla) throws Exception {
        esperarMilisegundos(1000);
        if(tecla == BotonCelular.ATRAS) driver.navigate().back();
        if(tecla == BotonCelular.ENTER) ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        if(tecla == BotonCelular.ABAJO) ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
        if(tecla == BotonCelular.ARRIBA) ((AndroidDriver<MobileElement>)driver).pressKey(new KeyEvent(AndroidKey.DPAD_UP));
        if(tecla == BotonCelular.DEL) ((AndroidDriver<MobileElement>)driver).pressKey(new KeyEvent(AndroidKey.DEL));
		log.info("Se presionó "+tecla);
	}


	/*------------------------------------------------------------------------------------------------
    |  Método: validarTextoDeElemento(String locator, String text)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Valida el texto que tiene un elemento de la página.
    |
    |  Parámetros:
    |	String locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
    |	String texto   -- Texto a validar.
    |	String nombreElemento (Opcional) -- En el log se visualiza mas claramente el nombre del elemento y no el xpath
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void validarTextoDeElemento(String locator, String text, String nombreElemento, Selector selector) throws Exception {
        if(selector == Selector.contieneTexto) Assertions.assertEquals(text, encontrarElementoPorTextoQueContiene(locator).getText());
        else if(selector == Selector.resourceId) Assertions.assertEquals(text, encontrarElementoPorResourceID(locator).getText());
        else throw new Exception("El selector que se indicó no coincide con los esperados: contieneTexto o resourceId");
		log.info("Se validó que "+text+" se visualiza en el elemento con locator: "+nombreElemento);
	}
	
	public void validarTextoDeElemento(By locator, String text, String nombreElemento) throws Exception {
		Assertions.assertEquals(text, encontrarElementoPorBy(locator).getText());
		log.info("Se validó que "+text+" se visualiza en el elemento: "+nombreElemento);
	}
	
	
	
	/*------------------------------------------------------------------------------------------------
    |  Método: validarContentDescDeElemento(String locator, String text)
    |
    |  Autor: Nicolas Rioseco
    |
    |  Definición:  Valida que un elemento de la página contenga en su contest-desc un texto específico.
    |
    |  Parámetros:
    |	String locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
    |	String texto   -- Texto a validar que exista en el elemento.
    |	String nombreElemento (Opcional) -- En el log se visualiza mas claramente el nombre del elemento y no el xpath
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void validarContentDescDeElemento(By locator, String text, String nombreElemento) throws Exception {
		String contentDesc = encontrarElementoPorBy(locator).getAttribute("content-desc");
		Assertions.assertTrue(contentDesc.contains(text));
		log.info("Se validó que "+text+" se visualiza en el elemento: "+nombreElemento);
	}
	
	/*------------------------------------------------------------------------------------------------
    |  Método: validarQueElementoContengaUnTexto(String locator, String text)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Valida que un elemento de la página contenga un texto.
    |
    |  Parámetros:
    |	String locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
    |	String texto   -- Texto a validar que exista en el elemento.
    |	String nombreElemento (Opcional) -- En el log se visualiza mas claramente el nombre del elemento y no el xpath
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
    public void validarQueElementoContengaUnTexto(String locator, String textoEsperado, String nombreElemento, Selector selector) throws Exception {
        String textoQueSeVisualiza;
        if(selector == Selector.contieneTexto) textoQueSeVisualiza = encontrarElementoPorTextoQueContiene(locator).getText();
        else if(selector == Selector.resourceId) textoQueSeVisualiza = encontrarElementoPorResourceID(locator).getText();
        else throw new Exception("El selector que se indicó no coincide con los esperados: contieneTexto o resourceId");
        Assertions.assertTrue(textoQueSeVisualiza.contains(textoEsperado), "El valor: \'"+textoEsperado+"\' no se encuentra dentro de: \'"+textoQueSeVisualiza+"\'");
		log.info("Se validó que "+textoEsperado+" se visualiza dentro del elemento: "+nombreElemento);
	}
	
	public void validarQueElementoContengaUnTexto(By locator, String textoEsperado, String nombreElemento) throws Exception {
		String textoQueSeVisualiza = encontrarElementoPorBy(locator).getText();
		Assertions.assertTrue(textoQueSeVisualiza.contains(textoEsperado), "El valor: \'"+textoEsperado+"\' no se encuentra dentro de: \'"+textoQueSeVisualiza+"\'");
		log.info("Se validó que "+textoEsperado+" se visualiza dentro del elemento: "+nombreElemento);
	}

	/*------------------------------------------------------------------------------------------------
    |  Método: seleccionarUnElementoDeUnCombo(String combo, String value)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Presiona un combo de la página y busca un elemento dentro de el.
    				Hace click en el combo y luego click en el valor dentro de el.
    |
    |  Parámetros:
    |	String combo   -- Xpath del combo a presionar.
    |	String texto   -- Xpath del valor a seleccionar dentro del combo.
    |	String nombreElemento (Opcional) -- En el log se visualiza mas claramente el nombre del elemento y no el xpath
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void seleccionarUnElementoDeUnCombo(String combo, String value, String elementoNombre, Selector selector) throws Exception {
        if(selector == Selector.contieneTexto){
            encontrarElementoPorTextoQueContiene(combo).click();
		    encontrarElementoPorTextoQueContiene(value).click();
        }
        else if(selector == Selector.resourceId){
            encontrarElementoPorResourceID(combo).click();
		    encontrarElementoPorResourceID(value).click();
        }
        else throw new Exception("El selector que se indicó no coincide con los esperados: contieneTexto o resourceId");
		log.info("Se seleccionó el valor: "+value+" del elemento: "+elementoNombre);
	}
	
	public void seleccionarUnElementoDeUnCombo(By combo, String value, String elementoNombre) throws Exception {
		encontrarElementoPorBy(combo).click();
		encontrarElementoPorTexto(value).click();
		log.info("Se seleccionó el valor: "+value+" del elemento: "+elementoNombre);
	}
	
	/*------------------------------------------------------------------------------------------------
	|  Método: hacerScrollHaciaUnElemento(String textoDeElementoABuscar)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Hace scroll dentro la página hasta el elemento que contenga el texto que le pasamos
    |
    |  Parámetros:
    |	String textoDeElementoABuscar -- Texto que contiene el elemento dentro de la página.	
    | 
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void hacerScrollHaciaUnElemento(String textoDeElementoABuscar, String ubicacion, Selector selector) throws Exception {
        esperarMilisegundos(2000);
        if (selector == Selector.texto) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("                      
                + "new UiSelector().text(\""+textoDeElementoABuscar+"\").instance(0));");
        if (selector == Selector.contieneTexto) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("                      
                + "new UiSelector().textContains(\""+textoDeElementoABuscar+"\").instance(0));");
        if (selector == Selector.className) driver.findElementByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().className(\"%1$s\").instance(%2$s));", textoDeElementoABuscar, ubicacion));
                	
		log.info("Se hizo scroll hasta el elemento con texto: "+textoDeElementoABuscar);
    }

    public void hacerScrollHaciaUnElemento(String textoDeElementoABuscar, String ubicacionElemento, String ubicacionScroll, Selector selector) throws Exception {
        esperarMilisegundos(2000);
        if (selector == Selector.texto) driver.findElementByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true).instance(%1$s)).scrollIntoView("                      
                + "new UiSelector().text(\"%2$s\").instance(%3$s));",ubicacionScroll ,textoDeElementoABuscar,ubicacionElemento));
        if (selector == Selector.contieneTexto) driver.findElementByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true).instance(%1$s)).scrollIntoView("                      
                + "new UiSelector().textContains(\"%2$s\").instance(%3$s));",ubicacionScroll ,textoDeElementoABuscar,ubicacionElemento));
        if (selector == Selector.className) driver.findElementByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true).instance(%1$s)).scrollIntoView("                      
        + "new UiSelector().className(\"%2$s\").instance(%3$s));", ubicacionScroll, textoDeElementoABuscar, ubicacionElemento));
                	
		log.info("Se hizo scroll hasta el elemento con texto: "+textoDeElementoABuscar);
	}
    
    public void hacerScrollHaciaUnElemento(String textoDeElementoABuscar, Selector selector) throws Exception {
        esperarMilisegundos(2000);
        if (selector == Selector.texto) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("                      
                + "new UiSelector().text(\""+textoDeElementoABuscar+"\").instance(0));");
        if (selector == Selector.contieneTexto) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("                      
                + "new UiSelector().textContains(\""+textoDeElementoABuscar+"\").instance(0));");
        if (selector == Selector.className) driver.findElementByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("                      
        + "new UiSelector().className(\"%1$s\").instance(%2$s));", textoDeElementoABuscar, 0));
        if (selector == Selector.contentDesc) driver.findElementByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("                      
                + "new UiSelector().scrollIntoView(description(\"%1$s\").instance(%2$s));", textoDeElementoABuscar, 0));
        
                	
		log.info("Se hizo scroll hasta el elemento con texto: "+textoDeElementoABuscar);
	}
	
	
	/*------------------------------------------------------------------------------------------------
	|  Método: hacerScrollHaciaArriba()
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Hace scroll hacia arriba hasta llegar al comienzo de la página
    |
    |  Parámetros:
    | 
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void hacerScrollHaciaArriba() throws Exception {
		Dimension dim = driver.manage().window().getSize();
		int x = dim.getWidth()/2;
		int startY = (int) (dim.getHeight() * 0.2);
		//int endY = (int) (dim.getHeight() * 0.8);
		esperarMilisegundos(1000);
		new TouchAction<>(driver)
		.press(PointOption.point(x,startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(934, 1660))
		.release().perform();
		log.info("Se hizo scroll hacia arriba");
    }
    
    public void hacerScrollAUnaPosicion() throws Exception {
        Dimension dim = driver.manage().window().getSize();
        int x = dim.getWidth()/2;
        int startY = (int) (dim.getHeight() * 0.2);
		int endY = (int) (dim.getHeight() * 0.8);
        // int startx = encontrarElemento(locatorInicial).getLocation().getX();
        // int starty = encontrarElemento(locatorInicial).getLocation().getY();
        // int endx = encontrarElemento(locatorFinal).getLocation().getX();
        // int endy = encontrarElemento(locatorFinal).getLocation().getY();
        System.out.println(x + " ::::::: " + startY + " ::::::: " + endY);

        new TouchAction<>(driver)
		.press(PointOption.point(x,startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(x, endY))
		.release().perform();
        
    }

	/*------------------------------------------------------------------------------------------------
	|  Método: estaHabilitadoElElemento(By locator)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Valida si el elemento esta habilitado
    |
    |  Parámetros: 
    |	By locator -- elemento a validar
    | 
    |  Retorna:
    |	Boolean -- True o False depende si esta habilitado o no
    *------------------------------------------------------------------------------------------------*/
    public Boolean estaHabilitadoElElemento(By locator) throws Exception {
        Boolean habilitado = false;
        for (int i = 0; i < timeOut; i++) {
            habilitado = encontrarElementoPorBy(locator).isEnabled();
            if (habilitado) break;
        }
		log.info("Elemento habilitado "+ habilitado.toString());
		return habilitado;
    }
    
    public Boolean estaVisibleElElemento(By locator) throws Exception {
        Boolean visible = false;
        for (int i = 0; i < timeOut; i++) {
            visible = encontrarElementoPorBy(locator).isDisplayed();
            if (visible) break;
        }
        log.info("Elemento visible "+ visible.toString());
        return visible;
    }
    
    public Boolean estaVisibleElElemento(String locator, Selector selector) throws Exception {
        Boolean visible = false;
        esperarElemento(locator, 60, selector);
        for (int i = 0; i < timeOut; i++) {
            if (selector == Selector.contieneTexto) visible = encontrarElementoPorTextoQueContiene(locator).isDisplayed();
            if (selector == Selector.resourceId) visible = encontrarElementoPorResourceID(locator).isDisplayed();
            if (selector == Selector.texto) visible = encontrarElementoPorTexto(locator).isDisplayed();
            if (visible) break;
        }
        log.info("Elemento visible "+ visible.toString());
        return visible;
	}
	
	
	/*------------------------------------------------------------------------------------------------
	|  Método: esperarElemento(By/String locator, long tiempo)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Valida si el elemento esta habilitado
    |
    |  Parámetros: 
    |	By/String locator -- elemento a esperar
    |	long tiempo -- Tiempo en segundos a esperar como máximo que aparezca el elemento
    | 
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void esperarElemento(By locator, long tiempo) throws Exception{
		WebDriverWait esperar = new WebDriverWait(driver, tiempo);
		esperar.until(ExpectedConditions.attributeToBe(locator, "displayed", "true"));
		log.info("Se esperó al elemento "+locator+" por "+tiempo+" segundos.");
	}
	
	public void esperarElemento(String locator, long tiempo, Selector selector) throws Exception{
        WebDriverWait esperar = new WebDriverWait(driver, tiempo);
        if(selector == Selector.contieneTexto) esperar.until(ExpectedConditions.attributeToBe(encontrarElementoPorTextoQueContiene(locator), "displayed", "true"));
        else if(selector == Selector.resourceId) esperar.until(ExpectedConditions.attributeToBe(encontrarElementoPorResourceID(locator), "displayed", "true"));
        else if (selector == Selector.texto) esperar.until(ExpectedConditions.attributeToBe(encontrarElementoPorTexto(locator), "displayed", "true"));
        else throw new Exception("El selector que se indicó no coincide con los esperados: contieneTexto, texto, resourceId");
		log.info("Se esperó al elemento "+locator+" por "+tiempo+" segundos.");
    }


    public void esperarElementoDesaparezca(String locator, int tiempoEspera, Selector selector, String nombreElemento) throws Exception {
        try {
            new WebDriverWait(driver, tiempoEspera).until(ExpectedConditions.invisibilityOf(encontrarElementoPorResourceID(locator)));
            log.info("Se espero hasta que el elemento "+nombreElemento+" desaparezca.");
        } catch (Exception e) {
            log.info("El elemento "+nombreElemento+" no se encontraba en el DOM.");
        }
    }
	
	/*------------------------------------------------------------------------------------------------
    |  Método: esperarMilisegundos(long milisegundos)
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Espera un tiempo fijo en milisegundos
    |
    |  Parámetros:
    |	long milisegundos -- Tiempo en milisegundos 
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void esperarMilisegundos(long milisegundos) throws InterruptedException {
		Thread.sleep(milisegundos);		
	}
	
	/*------------------------------------------------------------------------------------------------
    |  Método: eliminarTextoDeElemento(By locator)
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Dentro de un elemento a completar con texto, se dirige al final del texto y eliminar con el DELETE del teclado del celular todo el texto.
    |
    |  Parámetros:
    |	By/String locator -- elemento que contiene texto a eliminar
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void eliminarTextoDeElemento(By locator) throws Exception {
		encontrarElementoPorBy(locator).click();
		int longitudTexto = encontrarElementoPorBy(locator).getText().length();
		desplazarAlFInalYEliminarCaracteres(longitudTexto);
    }
    
    public void eliminarTextoDeElemento(String locator, Selector selector) throws Exception {
        int longitudTexto = 0;
        if(selector == Selector.contieneTexto){
            encontrarElementoPorTextoQueContiene(locator).click();
            longitudTexto = encontrarElementoPorTextoQueContiene(locator).getText().length();
        } else if (selector == Selector.resourceId){
            encontrarElementoPorResourceID(locator).click();
            longitudTexto = encontrarElementoPorResourceID(locator).getText().length();
        }
        desplazarAlFInalYEliminarCaracteres(longitudTexto);
    }
    
    public void desplazarAlFInalYEliminarCaracteres(int longitudTexto) throws InterruptedException {
        for (int i = 0; i < longitudTexto; i++) driver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
		for (int i = 0; i < longitudTexto; i++) driver.pressKey(new KeyEvent(AndroidKey.DEL));
		esperarMilisegundos(1000);
    }
	
	/*------------------------------------------------------------------------------------------------
    |  Método: bajarTeclado()
    |
    |  Autor: Emmanuel Kippes
    |
    |  Definición:  Minimiza el teclado del celular
    |
    |  Parámetros: -
    |
    |  Retorna: -
    *------------------------------------------------------------------------------------------------*/
	public void bajarTeclado() throws InterruptedException {
		driver.hideKeyboard();
		esperarMilisegundos(1000);
	}
 
    public String obtenerTextoElemento(String locator, Selector selector) throws Exception {
        String textoQueSeVisualiza = null;
        if(selector == Selector.resourceId) textoQueSeVisualiza = encontrarElementoPorResourceID(locator).getText();
        if(selector == Selector.contieneTexto) textoQueSeVisualiza = encontrarElementoPorTextoQueContiene(locator).getText();
        return textoQueSeVisualiza;
    }

}
