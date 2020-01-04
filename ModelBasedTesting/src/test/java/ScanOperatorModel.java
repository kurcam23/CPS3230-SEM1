import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;

public class ScanOperatorModel implements FsmModel {

    WebDriver browser;

    private ScanOperator scanOperator;

    ScanOperatorModel(WebDriver browser) {
        this.browser = browser;
        scanOperator = new ScanOperator(browser);
    }

    public Object getState() {
        return null;
    }

    public void reset(boolean b) {

    }
}
