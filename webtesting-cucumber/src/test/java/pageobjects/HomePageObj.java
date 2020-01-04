package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static test.cucumber.stepdefs.ScanMaltaStepDefs.sleep;

public class HomePageObj {

    static WebDriver browser;
    By loginText = By.partialLinkText("Hello. Sign in");
    By popUpClose = By.className("close-reveal-modal");


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
            loggedIn = !browser.findElement(loginText).isDisplayed();
        }else{
            loggedIn = true;
        }
        return loggedIn;
    }


}
