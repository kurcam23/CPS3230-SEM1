package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;
import static test.cucumber.stepdefs.ScanMaltaStepDefs.sleep;

public class HomePageObj {

    static WebDriver browser;
    By loginText = By.partialLinkText("YOUR ACCOUNT");
    By loggedOutText = By.partialLinkText("Hello. Sign in");
    By popUpClose = By.className("close-reveal-modal");
    By search = By.id("search");
    By searchButton = By.className("icon-search");


    public HomePageObj(WebDriver browser){
        HomePageObj.browser = browser;
    }

    public void closePopUp(){
        if(browser.findElements(popUpClose).size() != 0) {
            browser.findElement(popUpClose).click();
        }
    }

    public boolean loggedIn(){
        boolean loggedIn;
        if(browser.findElements(loginText).size() != 0) {
            if(browser.findElements(loggedOutText).size() != 0) {
                loggedIn = !browser.findElement(loggedOutText).isDisplayed();
            }else{
                loggedIn = true;
            }
        }else{
            loggedIn = false;
        }
        return loggedIn;
    }

    public void searchProduct(String product){
        browser.findElement(search).sendKeys(product);
        browser.findElement(searchButton).click();
    }
}
