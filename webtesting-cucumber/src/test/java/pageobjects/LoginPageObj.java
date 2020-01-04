package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static test.cucumber.stepdefs.ScanMaltaStepDefs.sleep;

public class LoginPageObj {

    static WebDriver browser;
    By yourAccount = By.partialLinkText("YOUR ACCOUNT");
    By email = By.id("email");
    By password = By.id("pass");
    By submitButton = By.id("send2");

    public LoginPageObj(WebDriver browser){
        LoginPageObj.browser = browser;
    }

    public void logIn(){
        browser.findElement(yourAccount).click();
        sleep(2);
        browser.findElement(email).sendKeys("kcam0061@gmail.com");
        browser.findElement(password).sendKeys("testing123");
        browser.findElement(submitButton).click();
    }
}
