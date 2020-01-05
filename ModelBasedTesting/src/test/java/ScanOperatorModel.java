import enums.ScanOperatorStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class ScanOperatorModel implements FsmModel {

    WebDriver browser;

    private ScanOperator sut;
    private ScanOperatorStates state;
    private boolean isLoggedIn, isSearches, isAddingProductToCart, isRemovingProductFromCart, isCheckedOut, isLoggedOut;

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
        return (getState().equals(ScanOperatorStates.LOGGED_OUT))||
                (getState().equals(ScanOperatorStates.SEARCHES)) ||
                (getState().equals(ScanOperatorStates.ADDING_PRODUCT_TO_CART)) ||
                (getState().equals(ScanOperatorStates.REMOVING_PRODUCT_FROM_CART)) ||
                ((getState().equals(ScanOperatorStates.CHECKOUT)));
    }
    public @Action void loggingIn() {
        sut.loggingIn();
        isLoggedIn = true;
        isLoggedOut = false;

        state = ScanOperatorStates.LOGGED_IN;
        assertEquals(isLoggedIn, sut.isLoggedIn());
    }

    public boolean loggingOutGuard() {
        return (getState().equals(ScanOperatorStates.LOGGED_IN)) ||
                (getState().equals(ScanOperatorStates.SEARCHES)) ||
                (getState().equals(ScanOperatorStates.ADDING_PRODUCT_TO_CART)) ||
                (getState().equals(ScanOperatorStates.REMOVING_PRODUCT_FROM_CART)) && isLoggedIn;
    }
    public @Action void loggingOut() {
        sut.loggingOut();
        isLoggedIn = false;
        isLoggedOut = true;

        state = ScanOperatorStates.LOGGED_OUT;
        assertEquals(isLoggedOut, sut.isLoggedOut());
    }

    public boolean searchesGuard(){
        return (getState().equals(ScanOperatorStates.LOGGED_IN)) ||
                (getState().equals(ScanOperatorStates.LOGGED_OUT)) ||
                (getState().equals(ScanOperatorStates.SEARCHES)) ||
                (getState().equals(ScanOperatorStates.ADDING_PRODUCT_TO_CART)) ||
                (getState().equals(ScanOperatorStates.REMOVING_PRODUCT_FROM_CART));
    }
    public @Action void searches(){
        isSearches = true;

        sut.searchProduct();
        state = ScanOperatorStates.SEARCHES;

        assertEquals(isSearches, sut.isSearches());
    }

    public boolean addingProductToCartGuard() {
        return  (getState().equals(ScanOperatorStates.SEARCHES));
    }
    public @Action
    void addingProductToCart() {
        isSearches = false;
        isAddingProductToCart = true;

        sut.addingToCart();
        state = ScanOperatorStates.ADDING_PRODUCT_TO_CART;

        assertEquals(isAddingProductToCart, sut.isAddingProductToCart());
    }

    public boolean removingProductFromCartGuard() {
        return  (getState().equals(ScanOperatorStates.REMOVING_PRODUCT_FROM_CART)) ||
                (getState().equals(ScanOperatorStates.ADDING_PRODUCT_TO_CART)) ;
    }
    public @Action
    void removingProductFromCart() {

        isAddingProductToCart = false;
        isRemovingProductFromCart = true;

        sut.removingFromCart();
        state = ScanOperatorStates.REMOVING_PRODUCT_FROM_CART;

        assertEquals(isRemovingProductFromCart, sut.isRemovingProductFromCart());
    }

    public boolean checkedOutGuard() {
        return  (getState().equals(ScanOperatorStates.ADDING_PRODUCT_TO_CART))  ||
                (getState().equals(ScanOperatorStates.REMOVING_PRODUCT_FROM_CART)) && isLoggedIn;
    }
    public @Action
    void checkedOut() {

        isRemovingProductFromCart = true;
        isCheckedOut = true;

        sut.checkOut();
        state = ScanOperatorStates.CHECKOUT;

        assertEquals(isCheckedOut, sut.isCheckOut());
    }

}
