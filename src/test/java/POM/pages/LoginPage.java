package POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    //Elements
    @FindBy(id ="user-name")
    WebElement userNameInput;

    @FindBy(id ="password")
    WebElement passwordInput;

    @FindBy(id ="login-button")
    WebElement loginBtn;

    @FindBy(className = ".login_logo")
    WebElement pageTitle;

    //Constructor
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //methods i.e. Action on the page
    public boolean isAt(){
        return pageTitle.isDisplayed();
    }

    public ProductPage login(String userName, String password){
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ProductPage(driver);
    }
}
