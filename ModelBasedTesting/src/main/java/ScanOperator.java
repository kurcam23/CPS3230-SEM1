import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class ScanOperator {

    WebDriver browser;
    boolean isLoggedIn = false;
    boolean isLoggedOut = true;
    boolean isSearches = false;
    boolean isAddingProductToCart = false;
    boolean isRemovingProductFromCart = false;
    boolean isCheckedOut = false;

    public ScanOperator(WebDriver browser) {
        this.browser = browser;
        browser.get("https://www.scanmalta.com/newstore/");
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    public void loggingIn() {
        isLoggedIn = true;
        isLoggedOut = false;

        browser.get("https://www.scanmalta.com/newstore/customer/account/logout/");
        sleep(2);
        browser.get("https://www.scanmalta.com/newstore/customer/account/login/");
        sleep(2);
        browser.findElement(By.id("email")).sendKeys("kcam0061@gmail.com");
        browser.findElement(By.id("pass")).sendKeys("testing123");
        browser.findElement(By.id("send2")).click();
        sleep(2);
    }



    public void loggingOut() {
        isLoggedIn = false;
        isLoggedOut = true;

        browser.get("https://www.scanmalta.com/newstore/customer/account/logout/");
        sleep(2);
    }

    public void searchProduct() {
        isSearches = true;
        browser.findElement(By.id("search")).clear();
        browser.findElement(By.id("search")).sendKeys("Laptop");
        browser.findElement(By.className("icon-search")).submit();
        sleep(2);
    }

    public void addingToCart() {
        isSearches = false;
        isAddingProductToCart = true;

        browser.findElements(By.className("item-image")).get(2).click();
        sleep(2);
        browser.findElement(By.id("product-addtocart-button")).click();
        sleep(2);
    }

    public boolean isLoggedIn() { return isLoggedIn; }
    public boolean isLoggedOut() { return isLoggedOut; }
    public boolean isSearches(){ return isSearches; }
    public boolean isAddingProductToCart() { return isAddingProductToCart; }
    public boolean isRemovingProductFromCart() { return isRemovingProductFromCart; }
    public boolean isCheckOut() { return isCheckedOut; }

    public void removingFromCart() {
        isAddingProductToCart = false;
        isRemovingProductFromCart = true;

        //Click the delete
        if(browser.findElements(By.className("secondary")).size() > 0){
            try{
                browser.findElement(By.className("secondary")).click();
            }catch(Exception e){
                System.out.println("Error: "+e);
            }

        }
        browser.findElement(By.className("btn-remove2")).click();
        sleep(1);
        browser.get("https://www.scanmalta.com/newstore/");
    }

    public void checkOut() {
        isRemovingProductFromCart = false;
        isCheckedOut = true;

        browser.findElement(By.className("icon-cart")).click();
        sleep(2);
        browser.findElement(By.className("btn-proceed-checkout")).click();
    }
}
