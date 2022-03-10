package L2_Mobile.test_FE.util.configurationSpring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "L2_Mobile.test_FE")
@PropertySource("classpath:/configProperties/application-${ambiente:uat}.properties")
public class TestConfig {

}
