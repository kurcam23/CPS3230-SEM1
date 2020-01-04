package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static test.cucumber.stepdefs.ScanMaltaStepDefs.sleep;

public class LoginPageObj {

    static WebDriver browser;
    By yourAccount = By.partialLinkText("YOUR ACCOUNT");
    By emailId = By.id("email");
    By passwordId = By.id("pass");
    By submitButton = By.id("send2");

    public LoginPageObj(WebDriver browser){
        LoginPageObj.browser = browser;
    }

    public void logIn(String email, String password){
        browser.findElement(yourAccount).click();
        sleep(2);
        browser.findElement(emailId).sendKeys(email);
        browser.findElement(passwordId).sendKeys(password);
        browser.findElement(submitButton).click();
    }
}
