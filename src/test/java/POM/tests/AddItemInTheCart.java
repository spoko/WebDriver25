package POM.tests;

import POM.pages.LoginPage;
import POM.pages.ProductPage;
import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddItemInTheCart extends TestUtil {

    @Test
    public void addItemInTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user",
                "secret_sauce");

        productPage.addItemToTheCart("bike-light");
        Assert.assertEquals(productPage.getItemsInTheCart(), 1,
                "Since we`ve added only one item");
    }
}
