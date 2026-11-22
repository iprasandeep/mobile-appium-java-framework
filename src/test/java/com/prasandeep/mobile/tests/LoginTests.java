package com.prasandeep.mobile.tests;

import com.prasandeep.mobile.base.BaseTest;
import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.pages.CatalogPage;
import com.prasandeep.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"smoke", "login"}, description = "Valid credentials land back on Products catalog")
    public void validLoginShowsProducts() {
        LoginPage loginPage = catalogPage.openLoginFromMenu();
        CatalogPage home = loginPage.loginAs(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password"));

        Assert.assertTrue(home.isProductsVisible(), "Products catalog should be visible after valid login");
    }

    @Test(groups = {"login"}, description = "Blank password keeps user on login and surfaces validation")
    public void blankPasswordShowsErrorState() {
        LoginPage loginPage = catalogPage.openLoginFromMenu()
                .enterUsername(ConfigReader.get("valid.username"))
                .enterPassword("")
                .submitInvalidLogin();

        Assert.assertTrue(
                loginPage.isLoginScreenDisplayed() || loginPage.isErrorVisible(),
                "Login screen or validation error should remain for blank password");
    }
}
