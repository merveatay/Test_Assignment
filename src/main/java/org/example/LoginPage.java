package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public LoginPage(WebDriver webDriver, WebDriver driver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void login(String username, String password) {

        webDriver.get("https://www.network.com.tr");
        Assert.assertEquals("NetWork",webDriver.getTitle());  // Control .get("https://www.network.com.tr")
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("btnSignIn"))).click();
        Assert.assertEquals("Giriş yap",webDriver.getTitle()); // Control Login page
        webDriver.findElement(By.id("email")).sendKeys(username);         // write e-mail
        webDriver.findElement(By.id("password")).sendKeys(password);     // write password
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton"))).click();
        Assert.assertEquals("NetWork",webDriver.getTitle());  // Control to Come back to Home page

    }
    public void search() {
        WebElement searchData = webDriver.findElement(By.id("searchData"));
        searchData.sendKeys("ceket"); //search for key "ceket" item
        searchData.sendKeys(Keys.ENTER);
    }
    public void secondPage() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pagedListContainer\"]/div[2]/div[2]/button"))).click(); //xpath of daha fazla göster btn

        WebElement currentPage = webDriver.findElement(By.xpath("//*[@id=\"currentPage\"]"));
        String page = currentPage.getAttribute("value").toString();
        Assert.assertTrue(page.equals("2")); //check if on page 2
    }
    public void basket() {


        // get the Search page price(for compare with basket price)
        WebElement productPrice = webDriver.findElement(By.cssSelector("input[class='product__price -actual']"));
        String value = productPrice.getAttribute("value").toString();   // Search page price

        //Click "Sepete ekle" button
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='product__button -addToCart btn -black']"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='class=\"button -primary header__basket--checkout header__basketModal -checkout\"']"))).click();  // go to basket
    }

    public void controlPrice() {

        WebElement productBasketPrice = webDriver.findElement(By.className("productPrice"));
        String basketValue = productBasketPrice.getAttribute("value").toString();
        Assert.assertTrue(basketValue.equals(basketValue));  // compare value of search page price with basket price
    }



    public void erase(){
        //erasing items in basket
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='cartItem__button -remove']"))).click();   ///Erase basket item
        WebElement emptyBasket = webDriver.findElement(By.cssSelector("h2[class='cartEmpty__title']"));
        String controlEmpty = emptyBasket.getText().toString();
        Assert.assertTrue("Sepetinizde ürün bulunmamaktadır",controlEmpty.equals("Sepetinizde ürün bulunmamaktadır")); 

        webDriver.quit();
    }
}




