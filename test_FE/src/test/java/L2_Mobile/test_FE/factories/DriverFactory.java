package L2_Mobile.test_FE.factories;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import L2_Mobile.test_FE.util.enums.SistemaOperativoMobile;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

@Component
@Scope("cucumber-glue")
public class DriverFactory {

	protected Logger log = LoggerFactory.getLogger(DriverFactory.class);
	protected AndroidDriver<MobileElement> driver;
	protected RemoteWebDriver driverRemoto;	
	protected File app;
	
	@Value("${servidor.remoto.url}")
	protected String urlRemota;
	
	@Value("${jenkins:false}")
	protected Boolean jenkins;
	
	@Value("${so}")
	private SistemaOperativoMobile mobile;
	
	@Value("${ejecucion.remota:false}")
	protected Boolean modoEjecucion;
	
	@Value("${device.name}")
	protected String deviceName;
	
	@Value("${udid}")
	protected String udid;
	
	@Value("${platform.name}")
	protected String platformName;
	
	@Value("${platform.version}")
	protected String platformVersion;
	
	//@Value("${app.package}")
	//protected String appPackage;
	
	//@Value("${app.activity}")
	//protected String appActivity;
	
	@Value("${apk.dir}")
	protected String apkDir;
	
	@Value("${apk.name}")
	protected String apkName;
	
	@Value("${time.out}")
	private long timeOut;
	
	@Autowired
	private AppiumDriverLocalService service;

	public AndroidDriver<MobileElement> getDriver() throws IOException, InterruptedException {
		if (!(modoEjecucion)) {
			switch (mobile) {
			case ANDROID:
				DesiredCapabilities clienteCapacidades = new DesiredCapabilities();
				clienteCapacidades.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
				clienteCapacidades.setCapability(MobileCapabilityType.UDID, udid);
				clienteCapacidades.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
				clienteCapacidades.setCapability("unicodeKeyboard", false);
				clienteCapacidades.setCapability("resetKeyboard", false);
				//clienteCapacidades.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 360);
				clienteCapacidades.setCapability("uiautomator2ServerInstallTimeout", 100000);
				//clienteCapacidades.setCapability("isHeadless", true);
				//clienteCapacidades.setCapability("fullReset", true);
				//clienteCapacidades.setCapability("noReset", false);
				
				//***CON APK
				app = new File(apkDir, apkName);
				clienteCapacidades.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
				clienteCapacidades.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
				//clienteCapacidades.setCapability("appWaitPackage", "com.android.packageinstaller");
				//clienteCapacidades.setCapability("appWaitActivity", ".permission.ui.GrantPermissionsActivity");
				//*** CON appPackage y appActivity
				//clienteCapacidades.setCapability("appPackage", "packageName");
				//clienteCapacidades.setCapability("appActivity", "activityName");
				
				log.info("Se configuro correctamente el entorno local. Generando el driver...");
				driver = new AndroidDriver<MobileElement>(service.getUrl(), clienteCapacidades);
				driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
				log.info("Se generó correctamente el driver");
				return driver;
				
			default:
				throw new IllegalArgumentException("Driver no encontrado entre los navegadores: " + mobile);
			}
		}else {
			throw new IllegalArgumentException("Ejecución remota en True. Debe ponerlo en False para ejecutar en un dispositivo local");
		}
	
	}

	public AndroidDriver<MobileElement> getDriverRemote() throws MalformedURLException {
		if(modoEjecucion) {
			    DesiredCapabilities device = new DesiredCapabilities();
			    device.setBrowserName("android");
		        device.setVersion("9.0");
		        device.setCapability("app", "http://ci.example.com/game2048.apk"); //APK from https://www.apkmirror.com/apk/androbaby/2048/2048-2-1-release/2048-2-1-android-apk-download/download/
		        device.setCapability("appPackage", "com.androbaby.game2048");
		        device.setCapability("appActivity", "com.androbaby.game2048.MainActivity");
		        device.setCapability("enableVNC", true);
		        device.setCapability("enableVideo", false);
		        
		        driver = new AndroidDriver<MobileElement>(new URL("http://192.168.99.100:4444/wd/hub"), device);
		        return driver;
		    }else {
			throw new IllegalArgumentException("Ejecución remota en False. Debe ponerlo en True para ejecutar en un dispositivo remoto");
		}
		
	}

}
