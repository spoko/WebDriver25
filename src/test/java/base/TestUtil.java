package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    public static String url, browser;
    private int implicitWait;

    @BeforeMethod
    public void beforeEachTest(){
        readConfig("src/test/resources/config.properties");
        setupDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        //this is possible to use, but it is better to use explicit wait
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    private void readConfig(String filePath){
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            browser = properties.getProperty("browser");
            url = properties.getProperty("testUrl");
            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupDriver(){
        switch (browser){
            case "firefox":
                driver = setupFirefoxDriver();
                break;
            case "edge":
                driver = setupEdgeDriver();
                break;
            default:
                driver = setupChromeDriver();
        }
    }

    private WebDriver setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private WebDriver setupEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
