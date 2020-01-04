package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class ScanMaltaStepDefs {

    WebDriver browser;

    @Given("^I am using Scan Malta website$")
    public void i_am_using_Scan_Malta_website() throws Throwable {
        browser.get("https://www.scanmalta.com/newstore/");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String query) throws Exception {
        browser.findElement(By.name("q")).sendKeys(query);
        browser.findElement(By.name("q")).submit();
    }

    @Then("^the title should be \"([^\"]*)\"$")
    public void the_title_should_be(String expectedTitle) throws Exception {
        assertEquals(expectedTitle, browser.getTitle());
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/users/kcam0061/Downloads/chromedriver.exe");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        //browser.quit();
    }

}
