import org.openqa.selenium.WebDriver;

class ScanOperator {
    WebDriver browser;

    public ScanOperator(WebDriver browser) {
        this.browser = browser;
        browser.get("https://www.scanmalta.com/newstore/");
    }

}
