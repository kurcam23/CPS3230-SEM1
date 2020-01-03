package test.system.google;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class GoogleTests {

    WebDriver browser;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/Users/kcam0061/Downloads/chromedriver");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }

    @Test
    public void testSimpleSearch() {
        browser.get("http://www.google.com");
        browser.findElement(By.name("q")).sendKeys("Malta");
        browser.findElement(By.name("btnK")).click();
        assertEquals("Malta - Google Search", browser.getTitle());
    }

}
