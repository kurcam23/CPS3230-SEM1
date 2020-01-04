package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

import pageobjects.CatalogSearchPageObj;
import pageobjects.HomePageObj;
import pageobjects.LoginPageObj;

public class ScanMaltaStepDefs {

    static WebDriver browser;
    HomePageObj homePageObj;
    LoginPageObj loginPageObj;
    CatalogSearchPageObj catalogSearchPageObj;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/users/kcam0061/Downloads/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://www.scanmalta.com/newstore/");
        homePageObj = new HomePageObj(browser);
        loginPageObj = new LoginPageObj(browser);
        catalogSearchPageObj = new CatalogSearchPageObj(browser);

        sleep(1);
        homePageObj.closePopUp();
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
    }

    @Given("I am a user on the website")
    public void iAmAUserOnTheWebsite() {
        assertFalse(homePageObj.loggedIn());
    }

    @When("I log in using valid credentials")
    public void iLogInUsingValidCredentials() {
        loginPageObj.logIn("kcam0061@gmail.com", "testing123");
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        sleep(2);
        assertTrue(homePageObj.loggedIn());
    }

    @When("I log in using invalid credentials")
    public void iLogInUsingInvalidCredentials() {
        loginPageObj.logIn("invalid@Email.com", "invalidPassword");
    }

    @Then("I should not be logged in")
    public void iShouldNotBeLoggedIn() {
        sleep(2);
        assertFalse(homePageObj.loggedIn());
    }

    @Given("I am a logged in user on the website")
    public void iAmALoggedInUserOnTheWebsite() {
        sleep(1);
        loginPageObj.logIn("kcam0061@gmail.com", "testing123");
        sleep(2);
        assertTrue(homePageObj.loggedIn());
    }

    @When("I search for a product")
    public void iSearchForAProduct() {
        homePageObj.searchProduct("laptop");
    }

    @And("I select the first product in the list")
    public void iSelectTheFirstProductInTheList() {
        catalogSearchPageObj.selectProduct();
    }

    @Then("I should see the product details")
    public void iShouldSeeTheProductDetails() {
        catalogSearchPageObj.productSelected();
    }

    @After
    public void teardown() {
        browser.quit();
    }
}
