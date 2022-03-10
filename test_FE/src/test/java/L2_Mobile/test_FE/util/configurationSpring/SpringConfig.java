package L2_Mobile.test_FE.util.configurationSpring;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import L2_Mobile.test_FE.factories.DriverFactory;
import L2_Mobile.test_FE.factories.ServiceAppiumFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

@Configuration
public class SpringConfig {
	
	@Value("${time.out}")
	private int timeOut;
	
	@Autowired
	@Lazy
	private ServiceAppiumFactory servicioAppium;
	
	@Autowired
	@Lazy
	private DriverFactory driverFactory;
	
	@Bean(destroyMethod="stop")
	@Scope("cucumber-glue")
	public AppiumDriverLocalService iniciarServicioAppium(){return servicioAppium.iniciaService();}
	
	@Scope("cucumber-glue")
	@Bean(destroyMethod="quit")
	@DependsOn("iniciarServicioAppium")
	@ConditionalOnProperty(value="ejecucion.remota", havingValue = "false")
	public AndroidDriver<MobileElement> getDriver() throws IOException, InterruptedException {return driverFactory.getDriver();}
	
	@Scope("cucumber-glue")
	@Bean(destroyMethod="quit")
	@DependsOn("iniciarServicioAppium")
	@ConditionalOnProperty(value="ejecucion.remota", havingValue = "true")
	public RemoteWebDriver getDriverRemote() throws MalformedURLException {return driverFactory.getDriverRemote();}
	
	@Scope("cucumber-glue")
	@Bean
	@DependsOn("getDriver")
	@ConditionalOnProperty(value="ejecucion.remota", havingValue = "false")
	public WebDriverWait waitFor() throws IOException, InterruptedException {return new WebDriverWait(getDriver(), timeOut);}
	
	@Scope("cucumber-glue")
	@Bean
	@DependsOn("getDriverRemote")
	@ConditionalOnProperty(value="ejecucion.remota", havingValue = "true")
	public WebDriverWait waitForRemote() throws MalformedURLException {return new WebDriverWait(getDriverRemote(), timeOut);}

}
