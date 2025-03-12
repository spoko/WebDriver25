package POM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";
    private final static String BASE_REMOVE_PRODUCT_ID = "remove-sauce-labs-";

    @FindBy(css = ".shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(className = "title")
    WebElement pageTitle;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return pageTitle.isDisplayed();
    }

    public void addItemToTheCart(String itemName){
        WebElement itemToBeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + itemName));
        itemToBeAdded.click();
    }

    public void removeItemFromTheCart(String itemName){
        WebElement itemToBeRemoved = driver.findElement(By.id(BASE_REMOVE_PRODUCT_ID + itemName));
        itemToBeRemoved.click();
    }

    public int getItemsInTheCart(){
        return Integer.parseInt(shoppingCartBadge.getText());
    }
}
