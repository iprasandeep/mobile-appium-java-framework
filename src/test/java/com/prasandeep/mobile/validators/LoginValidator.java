package com.prasandeep.mobile.validators;

import com.prasandeep.mobile.actions.LoginActions;
import org.testng.Assert;

/** Assertions for login outcomes. */
public class LoginValidator {

    private final LoginActions login;

    public LoginValidator(LoginActions login) {
        this.login = login;
    }

    public void assertLoginScreenOrErrorVisible() {
        Assert.assertTrue(
                login.isLoginScreenDisplayed() || login.isErrorVisible(),
                "Login screen or validation error should be visible");
    }

    public void assertStillOnLoginScreen() {
        Assert.assertTrue(login.isLoginScreenDisplayed(),
                "User should remain on the login screen");
    }

    public void assertInvalidCredentialsError() {
        Assert.assertTrue(
                login.isErrorVisible() || login.isLoginScreenDisplayed(),
                "Expected error state or login screen after invalid credentials");
        if (login.isErrorVisible()) {
            String message = login.readErrorMessage().toLowerCase();
            Assert.assertTrue(
                    message.contains("credential") || message.contains("password") || message.contains("match"),
                    "Error text should mention credentials/password mismatch. Actual: " + message);
        }
    }
}
