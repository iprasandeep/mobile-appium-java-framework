package com.prasandeep.mobile.tests;

import com.prasandeep.mobile.actions.CatalogActions;
import com.prasandeep.mobile.actions.CheckoutActions;
import com.prasandeep.mobile.actions.LoginActions;
import com.prasandeep.mobile.base.BaseTest;
import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.validators.CatalogValidator;
import com.prasandeep.mobile.validators.CheckoutValidator;
import com.prasandeep.mobile.validators.LoginValidator;
import org.testng.annotations.Test;

/**
 * Sanity suite — all framework scenarios in one class for a single green/red gate.
 * Individual modules remain in LoginTests / ErrorStateTests / FormInputTests.
 */
public class SanitySuiteTests extends BaseTest {

    // --- Login ---

    @Test(priority = 1, groups = {"sanity", "smoke", "login"},
            description = "Valid credentials land back on Products catalog")
    public void validLoginShowsProducts() {
        CatalogActions home = catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
        new CatalogValidator(home).assertProductsVisible();
    }

    @Test(priority = 2, groups = {"sanity", "login"},
            description = "Blank password keeps user on login and surfaces validation")
    public void blankPasswordShowsErrorState() {
        LoginActions loginScreen = catalog.openLoginFromMenu()
                .enterUsername(ConfigReader.get("valid.username"))
                .enterPassword("")
                .submitLoginExpectingError();
        new LoginValidator(loginScreen).assertLoginScreenOrErrorVisible();
    }

    // --- Error states ---

    @Test(priority = 3, groups = {"sanity", "negative", "login"},
            description = "Invalid credentials show an error message")
    public void invalidCredentialsShowError() {
        LoginActions loginScreen = catalog.openLoginFromMenu()
                .enterUsername(ConfigReader.get("invalid.username"))
                .enterPassword(ConfigReader.get("invalid.password"))
                .submitLoginExpectingError();
        new LoginValidator(loginScreen).assertInvalidCredentialsError();
    }

    @Test(priority = 4, groups = {"sanity", "negative", "login"},
            description = "Blank username keeps user on login with validation")
    public void blankUsernameShowsValidation() {
        LoginActions loginScreen = catalog.openLoginFromMenu()
                .enterUsername("")
                .enterPassword(ConfigReader.get("valid.password"))
                .submitLoginExpectingError();
        new LoginValidator(loginScreen).assertLoginScreenOrErrorVisible();
    }

    // --- Forms ---

    @Test(priority = 5, groups = {"sanity", "forms", "smoke"},
            description = "Logged-in user can reach checkout address form")
    public void checkoutAddressFormIsReachable() {
        catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
        CheckoutActions form = catalog.openFirstProduct().addToCart().openCart().proceedToCheckout();
        new CheckoutValidator(form).assertFormDisplayed();
    }

    @Test(priority = 6, groups = {"sanity", "forms"},
            description = "Valid address fields can be filled on checkout form")
    public void fillValidCheckoutAddressFields() {
        catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
        CheckoutActions form = catalog.openFirstProduct().addToCart().openCart().proceedToCheckout()
                .fillValidAddress();
        new CheckoutValidator(form).assertFormStillVisibleAfterFill();
        form.submitToPayment();
    }

    @Test(priority = 7, groups = {"sanity", "forms", "negative"},
            description = "Submitting empty required name keeps user on form")
    public void emptyRequiredNameKeepsFormVisible() {
        catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
        CheckoutActions form = catalog.openFirstProduct().addToCart().openCart().proceedToCheckout()
                .enterAddressLine1("123 Automation Street")
                .enterCity("Bengaluru")
                .enterZip("560001")
                .enterCountry("India")
                .submitToPayment();
        new CheckoutValidator(form).assertRequiredNameValidationKeepsForm();
    }
}
