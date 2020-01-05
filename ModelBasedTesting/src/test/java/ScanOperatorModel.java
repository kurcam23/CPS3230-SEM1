import enums.ScanOperatorStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class ScanOperatorModel implements FsmModel {

    WebDriver browser;

    private ScanOperator sut;
    private ScanOperatorStates state;
    private boolean isLoggedIn,isSearches, isAddingProductToCart, isRemovingProductFromCart, isCheckedOut, isLoggedOut;

    ScanOperatorModel(WebDriver browser) {
        this.browser = browser;
        sut = new ScanOperator(browser);
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
            sut = new ScanOperator(browser);
        }
    }

    public boolean loggingInGuard() {
        return (isLoggedOut);
    }
    public @Action
    void loggingIn() {

        sut.loggingIn();

        isLoggedIn = true;
        isLoggedOut = false;
        isSearches = false;
        isAddingProductToCart = false;
        isRemovingProductFromCart = false;
        isCheckedOut = false;

        state = ScanOperatorStates.LOGGED_IN;

        assertEquals(isLoggedIn, sut.isLoggedIn());
    }
}
