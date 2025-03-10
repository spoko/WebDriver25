package firstLoginTests;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ListenerComparator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UnSuccessfulLogin extends TestUtil {

    @Test(dataProvider = "wrongUsers")
    public void unSuccessfulLogin(String userName, String password){
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name"))
                .sendKeys(userName);

        driver.findElement(By.cssSelector("[placeholder=Password]")).click();
        driver.findElement(By.cssSelector("[placeholder=Password]")).clear();
        driver.findElement(By.cssSelector("[placeholder=Password]"))
                .sendKeys(password);

        driver.findElement((By.id("login-button"))).click();

        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test=error]"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers() {
        try {
            CSVReader csvReader =
                    new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvResult = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++) {
                csvResult[i] = csvData.get(i);
            }
            return csvResult;
        } catch (CsvException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
