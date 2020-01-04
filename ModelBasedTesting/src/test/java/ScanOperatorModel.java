import enums.ScanOperatorStates;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;

public class ScanOperatorModel implements FsmModel {

    WebDriver browser;

    private ScanOperator scanOperator;
    private ScanOperatorStates state;
    private boolean isLoggedIn,isSearches, isAddingProductToCart, isRemovingProductFromCart, isCheckedOut, isLoggedOut;

    ScanOperatorModel(WebDriver browser) {
        this.browser = browser;
        scanOperator = new ScanOperator(browser);
    }

    public Object getState() {
        return state;
    }

    public void reset(boolean b) {
        state = ScanOperatorStates.LOGGED_OUT;
        isLoggedIn = false;
        isSearches = false;
        isAddingProductToCart = false;
        isRemovingProductFromCart = false;
        isCheckedOut = false;
        isLoggedOut = true;
        if(b){
            scanOperator = new ScanOperator(browser);
        }
    }
}
