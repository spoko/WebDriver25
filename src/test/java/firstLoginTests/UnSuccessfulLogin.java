package firstLoginTests;

import base.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnSuccessfulLogin extends TestUtil {

    @Test
    public void unSuccessfulLogin(){
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user1");

        driver.findElement(By.cssSelector("[placeholder=Password]")).click();
        driver.findElement(By.cssSelector("[placeholder=Password]")).clear();
        driver.findElement(By.cssSelector("[placeholder=Password]")).sendKeys("secret_sauce");

        driver.findElement((By.id("login-button"))).click();

        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test=error]"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
