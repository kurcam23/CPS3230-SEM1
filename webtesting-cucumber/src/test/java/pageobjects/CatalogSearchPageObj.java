package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class CatalogSearchPageObj {

    static WebDriver browser;
    String title = "";
    By itemTitle = By.className("item-title");
    By itemImage = By.className("item-image");

    public CatalogSearchPageObj(WebDriver browser){
        CatalogSearchPageObj.browser = browser;
    }

    public void selectProduct() {
        title = browser.findElement(itemTitle).getText();
        browser.findElement(itemImage).click();
    }

    public void productSelected() {
        assertEquals("SCAN | Your Trusted Choice - " + title,browser.getTitle());
    }
}
