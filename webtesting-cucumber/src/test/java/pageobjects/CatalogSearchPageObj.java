package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static test.cucumber.stepdefs.ScanMaltaStepDefs.sleep;

public class CatalogSearchPageObj {

    static WebDriver browser;
    String title = "";
    By itemTitle = By.className("item-title");
    By itemImage = By.className("item-image");
    By addProductButton = By.id("product-addtocart-button");
    By qtyList = By.id("qty_chosen");
    By listItem = By.className("active-result");

    public CatalogSearchPageObj(WebDriver browser){
        CatalogSearchPageObj.browser = browser;
    }

    public void selectProduct() {
        title = browser.findElement(itemTitle).getText();
        sleep(3);
        browser.findElements(itemImage).get(1).click();
    }

    public void productSelected() {
        assertEquals("SCAN | Your Trusted Choice - " + title,browser.getTitle());
    }

    public void buyProduct(int qty) {
        browser.findElement(qtyList).click();
        browser.findElements(listItem).get(qty-1).click();
        browser.findElement(addProductButton).click();
    }
}
