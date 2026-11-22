package com.prasandeep.mobile.tests;

import com.prasandeep.mobile.actions.LoginActions;
import com.prasandeep.mobile.base.BaseTest;
import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.validators.LoginValidator;
import org.testng.annotations.Test;

/** Thin negative tests for login error states. */
public class ErrorStateTests extends BaseTest {

    @Test(groups = {"negative", "login"}, description = "Invalid credentials show an error message")
    public void invalidCredentialsShowError() {
        LoginActions loginScreen = catalog.openLoginFromMenu()
                .enterUsername(ConfigReader.get("invalid.username"))
                .enterPassword(ConfigReader.get("invalid.password"))
                .submitLoginExpectingError();

        new LoginValidator(loginScreen).assertInvalidCredentialsError();
    }

    @Test(groups = {"negative", "login"}, description = "Unknown user cannot authenticate")
    public void unknownUserCannotLogin() {
        LoginActions loginScreen = catalog.openLoginFromMenu()
                .enterUsername("not_a_real_user@example.com")
                .enterPassword("WrongPass123!")
                .submitLoginExpectingError();

        new LoginValidator(loginScreen).assertStillOnLoginScreen();
    }
}
