import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class ScanOperator {

    WebDriver browser;
    boolean isLoggedIn = false;
    boolean isLoggedOut = true;
    boolean isSearches = false;
    boolean isAddingProductToCart = false;
    boolean isRemovingProductFromCart = false;
    boolean isCheckedOut = false;

    public ScanOperator(WebDriver browser) {
        this.browser = browser;
        browser.get("https://www.scanmalta.com/newstore/");
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    public void loggingIn() {
        isLoggedIn = true;
        isLoggedOut = false;

        browser.get("https://www.scanmalta.com/newstore/customer/account/logout/");
        sleep(2);
        browser.get("https://www.scanmalta.com/newstore/customer/account/login/");
        sleep(2);
        browser.findElement(By.id("email")).sendKeys("kcam0061@gmail.com");
        browser.findElement(By.id("pass")).sendKeys("testing123");
        browser.findElement(By.id("send2")).click();
        sleep(2);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void loggingOut() {
        isLoggedIn = false;
        isLoggedOut = true;

        browser.get("https://www.scanmalta.com/newstore/customer/account/logout/");
        sleep(2);
    }

    public boolean isLoggedOut() {
        return isLoggedOut;
    }
}
