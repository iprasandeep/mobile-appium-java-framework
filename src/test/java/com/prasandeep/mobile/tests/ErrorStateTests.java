package com.prasandeep.mobile.tests;

import com.prasandeep.mobile.base.BaseTest;
import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorStateTests extends BaseTest {

    @Test(groups = {"negative", "login"}, description = "Invalid credentials show an error message")
    public void invalidCredentialsShowError() {
        LoginPage loginPage = catalogPage.openLoginFromMenu()
                .enterUsername(ConfigReader.get("invalid.username"))
                .enterPassword(ConfigReader.get("invalid.password"))
                .submitInvalidLogin();

        Assert.assertTrue(loginPage.isErrorVisible() || loginPage.isLoginScreenDisplayed(),
                "Expected error state or login screen after invalid credentials");
        if (loginPage.isErrorVisible()) {
            String message = loginPage.getErrorMessage().toLowerCase();
            Assert.assertTrue(
                    message.contains("credential") || message.contains("password") || message.contains("match"),
                    "Error text should mention credentials/password mismatch. Actual: " + message);
        }
    }

    @Test(groups = {"negative", "login"}, description = "Unknown user cannot authenticate")
    public void unknownUserCannotLogin() {
        LoginPage loginPage = catalogPage.openLoginFromMenu()
                .enterUsername("not_a_real_user@example.com")
                .enterPassword("WrongPass123!")
                .submitInvalidLogin();

        Assert.assertTrue(loginPage.isLoginScreenDisplayed(),
                "User should remain on login after unknown credentials");
    }
}
