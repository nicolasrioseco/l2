package L2_Mobile.test_FE.stepdefinitions;

import java.io.File;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.TestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import L2_Mobile.test_FE.util.enums.SistemaOperativoMobile;
import L2_Mobile.test_FE.util.support.ManejoErrores;
import L2_Mobile.test_FE.util.enums.TomarCaptura;
import L2_Mobile.test_FE.pages.AppiumBase;
import L2_Mobile.test_FE.pages.Login;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

@Scope("cucumber-glue")
public class ReporteHook extends StepBasico{
	
	@Value("${tomar.captura:cadaPaso}")
	private TomarCaptura tomarCaptura;
	
	@Autowired
	private Login paginaLogin;
	
	//@Value("${tomar.captura:nunca}")
	//private TomarCaptura tomarCaptura;
	
	@Value("${so}")
	private SistemaOperativoMobile mobile;
	
	@Value("${ejecucion.remota:false}")
	protected Boolean modoEjecucion;
	
	private ExtentReports extent = new ExtentReports();
	
	private ExtentTest test;
	private ExtentTest step;
	
	private String nombreEscenario;
	
	@Autowired
	private ManejoErrores manejoErrores;

	protected AndroidDriver<MobileElement> driver;
	
	@Autowired
	 public ReporteHook(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
	}
	
	@Before
	public void iniciarEscenarioLog(Scenario scenario) throws InterruptedException {
		nombreEscenario = scenario.getName().toUpperCase();
    	this.test = extent.createTest(nombreEscenario);
		test.log(test.getStatus(), "<-- EL ESCENARIO: "+nombreEscenario+" COMENZO. -->");
	}
	
	/*@After
	public void tomarCapturaCuandoFallaEscenario(Scenario escenario) throws Exception {
		//if (escenario.isFailed() && TomarCaptura.fallaEscenario == tomarCaptura) {
	//		capturarPantalla(escenario);
		//}
	}
	
	@After
	public void finalizaEscenarioLog(Scenario scenario) throws Exception {
		try {
	    	ExtentTest step = test.createNode(scenario.getId());
	    	File scrFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	step.addScreenCaptureFromPath(scrFile.getPath());
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}
		
		
	
	@After
	public void tomarCapturaCuandoFinalizaEscenario(Scenario escenario) throws Exception {
		if (TomarCaptura.finalizaEscenario == tomarCaptura) {
			capturarPantalla(escenario);
		}
		
	}*/
	
	@AfterStep
	public void tomarCapturaPorCadaPaso(Scenario scenario) throws Exception {
		if (TomarCaptura.cadaPaso == tomarCaptura) {
			capturarPantalla(scenario);
		}
	}
	
	public void capturarPantalla(Scenario scenario) throws Exception {
		try {
			this.step = test.createNode("step: " + scenario.getId());
	    	File scrFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	step.addScreenCaptureFromPath(scrFile.getPath());
		} catch (Exception e) {
			manejarError(e);
			throw e;
		}
	}

}
