package L2_Mobile.test_FE;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import com.vimalselvam.cucumber.listener.Reporter;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class RunCucumberTest {

	
    @AfterAll
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Android");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }
}
