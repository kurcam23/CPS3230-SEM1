package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static test.cucumber.stepdefs.ScanMaltaStepDefs.sleep;

public class CartPageObj {

    static WebDriver browser;
    By removeButton = By.className("btn-remove2");

    public CartPageObj(WebDriver browser){
        CartPageObj.browser = browser;
    }

    public void removeItem() {
        sleep(2);
        browser.findElement(removeButton).click();
    }
}
