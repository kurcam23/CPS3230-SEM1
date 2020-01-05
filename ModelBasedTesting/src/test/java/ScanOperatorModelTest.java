import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class ScanOperatorModelTest {
    WebDriver browser;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/users/kcam0061/Downloads/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
    }

    @After
    public void teardown() { browser.quit(); }

    @Test
    public void ScanMaltaTestRunner() {
        GreedyTester tester = new GreedyTester(new ScanOperatorModel(browser));
        tester.setRandom(new Random());
        tester.generate(500);
        tester.buildGraph();
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.printCoverage();
    }
}
