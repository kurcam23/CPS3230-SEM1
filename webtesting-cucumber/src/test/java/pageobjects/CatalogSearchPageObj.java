package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static test.cucumber.stepdefs.ScanMaltaStepDefs.sleep;

public class CatalogSearchPageObj {

    static WebDriver browser;
    String title = "";
    By itemTitle = By.className("item-title");
    By itemImage = By.className("item-image");
    By addProductButton = By.id("product-addtocart-button");

    public CatalogSearchPageObj(WebDriver browser){
        CatalogSearchPageObj.browser = browser;
    }

    public void selectProduct() {
        title = browser.findElement(itemTitle).getText();
        sleep(1);
        browser.findElements(itemImage).get(1).click();
    }

    public void productSelected() {
        assertEquals("SCAN | Your Trusted Choice - " + title,browser.getTitle());
    }

    public void buyProduct() {
        browser.findElement(addProductButton).click();
    }
}
