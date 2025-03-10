package firstLoginTests;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SuccessfulLogin extends TestUtil {

    @Test(dataProvider = "correctUsers")
    public void successfulLogin(String userName){
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(userName);

        driver.findElement(By.cssSelector("[placeholder=Password]")).click();
        driver.findElement(By.cssSelector("[placeholder=Password]")).clear();
        driver.findElement(By.cssSelector("[placeholder=Password]")).sendKeys("secret_sauce");

        driver.findElement((By.id("login-button"))).click();

        //Explicit Wait:
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(1));

        WebElement productsPageTitle = driver.findElement(By.className("title"));
        explicitWait.until(ExpectedConditions.visibilityOf(productsPageTitle));//usage of the explicit wait

        Assert.assertTrue(productsPageTitle.isDisplayed());
    }

    @DataProvider(name = "correctUsers")
    public Object[] getCorrectUsers(){
        return new Object[] {
                "standard_user",
                "problem_user",
                "performance_glitch_user",
                "error_user",
                "visual_user"
        };
    }
}
