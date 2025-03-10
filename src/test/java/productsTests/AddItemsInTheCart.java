package productsTests;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddItemsInTheCart extends TestUtil {
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";

    @Test(dataProvider = "shoppingItems")
    public void addProductsToCart(String item){
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement((By.cssSelector("[placeholder=Password]")));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();

        WebElement itemToBeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + item));
        itemToBeAdded.click();

        WebElement shoppingCartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        //WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_badge")); //the same as above line

        Assert.assertEquals(shoppingCartBadge.getText(), "1",
                "Since we`ve added only one item so far");
    }

    @DataProvider(name = "shoppingItems")
    public Object[] getShoppingCartItems(){
        return new Object[] {
                "bike-light",
                "backpack",
                "fleece-jacket"
        };
    }
}
