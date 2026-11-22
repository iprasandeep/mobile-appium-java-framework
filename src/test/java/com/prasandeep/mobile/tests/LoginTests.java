package com.prasandeep.mobile.tests;

import com.prasandeep.mobile.actions.CatalogActions;
import com.prasandeep.mobile.actions.LoginActions;
import com.prasandeep.mobile.base.BaseTest;
import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.validators.CatalogValidator;
import com.prasandeep.mobile.validators.LoginValidator;
import org.testng.annotations.Test;

/** Thin tests: orchestrate actions + validators only. */
public class LoginTests extends BaseTest {

    @Test(groups = {"smoke", "login"}, description = "Valid credentials land back on Products catalog")
    public void validLoginShowsProducts() {
        CatalogActions home = catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        new CatalogValidator(home).assertProductsVisible();
    }

    @Test(groups = {"login"}, description = "Blank password keeps user on login and surfaces validation")
    public void blankPasswordShowsErrorState() {
        LoginActions loginScreen = catalog.openLoginFromMenu()
                .enterUsername(ConfigReader.get("valid.username"))
                .enterPassword("")
                .submitLoginExpectingError();

        new LoginValidator(loginScreen).assertLoginScreenOrErrorVisible();
    }
}
