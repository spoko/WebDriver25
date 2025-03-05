package firstLoginTests;

import base.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessfulLogin extends TestUtil {

    @Test
    public void successfulLogin(){
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.cssSelector("[placeholder=Password]")).click();
        driver.findElement(By.cssSelector("[placeholder=Password]")).clear();
        driver.findElement(By.cssSelector("[placeholder=Password]")).sendKeys("secret_sauce");

        driver.findElement((By.id("login-button"))).click();

        WebElement productsPageTitle = driver.findElement(By.className("title"));

        Assert.assertTrue(productsPageTitle.isDisplayed());
    }
}
