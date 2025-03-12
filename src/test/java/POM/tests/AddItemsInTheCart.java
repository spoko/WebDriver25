package POM.tests;

import POM.pages.LoginPage;
import POM.pages.ProductPage;
import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddItemsInTheCart extends TestUtil {

    @Test
    public void addItemsInTheCart(){
        SoftAssert softAssert = new SoftAssert();

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user",
                "secret_sauce");

        productPage.addItemToTheCart("bike-light");

        softAssert.assertEquals(productPage.getItemsInTheCart(), 1,
                "Since we`ve added only one item");

        productPage.addItemToTheCart("backpack");

        softAssert.assertEquals(productPage.getItemsInTheCart(), 2,
                "Since we`ve added two items");

        softAssert.assertAll();
    }
}
