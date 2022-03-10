package L2_Mobile.test_FE.factories;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

@Service
@Scope("cucumber-glue")
public class ServiceAppiumFactory {
	
	protected AppiumDriverLocalService service;
	protected Logger log = LoggerFactory.getLogger(ServiceAppiumFactory.class);
	
	@Value("${ip.adress}")
	protected String iPAdress;
	
	@Value("${port}")
	protected int puerto;
	
	public AppiumDriverLocalService iniciaService() {
		log.info("Iniciando el servicio Appium...");
		return configurarServicioAppium();
	}
	
	public AppiumDriverLocalService configurarServicioAppium() {
		AppiumServiceBuilder builder;
		log.info("Construyendo el servicio Appium");
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(iPAdress);
		builder.usingPort(puerto);
		//builder.withAppiumJS(new File("/home/nico/Android/Sdk/"));
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error"); //debug, error, info
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		log.info("Servicio de Appium iniciado en la direccion "+ service.getUrl());	
		return service;
	}
	
	public boolean chequearSiServicioAppiumEstaCorriendo(int puerto) {
		
		boolean estaServicioAppiumCorriendo = false;
		ServerSocket socketServicio;
		try {
			socketServicio = new ServerSocket(puerto);
			socketServicio.close();
		} catch (IOException e) {
			//Si entra por aca significa que el servicio esta corriendo
			estaServicioAppiumCorriendo = true;
		} finally {
			socketServicio = null;
		}
		return estaServicioAppiumCorriendo;
	}
	
	public void pararServicioAppium() {
		service.stop();
		log.info("Servicio de appium detenido");	
	}
}
