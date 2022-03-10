package L2_Mobile.test_FE.stepdefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import L2_Mobile.test_FE.utils.support.ManejoErrores;

public class StepBasico {

    @Autowired
    private ManejoErrores manejoErrores;

    protected static final Logger log = LoggerFactory.getLogger(StepBasico.class);

    protected void manejarError(Exception e) {
        //String message = manejoErrores.getMensajeDeExcepcion(e);
        manejoErrores.adherirErrorAlReporteAllure(e.toString());
        log.error(e.toString());
	}
    
    
    
}
