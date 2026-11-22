package com.prasandeep.mobile.tests;

import com.prasandeep.mobile.actions.CheckoutActions;
import com.prasandeep.mobile.base.BaseTest;
import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.validators.CheckoutValidator;
import org.testng.annotations.Test;

/** Thin form tests: navigate via actions, assert via validators. */
public class FormInputTests extends BaseTest {

    @Test(groups = {"forms", "smoke"}, description = "Logged-in user can reach checkout address form")
    public void checkoutAddressFormIsReachable() {
        catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        CheckoutActions form = catalog
                .openFirstProduct()
                .addToCart()
                .openCart()
                .proceedToCheckout();

        new CheckoutValidator(form).assertFormDisplayed();
    }

    @Test(groups = {"forms"}, description = "Valid address fields can be filled on checkout form")
    public void fillValidCheckoutAddressFields() {
        catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        CheckoutActions form = catalog
                .openFirstProduct()
                .addToCart()
                .openCart()
                .proceedToCheckout()
                .fillValidAddress();

        new CheckoutValidator(form).assertFormStillVisibleAfterFill();
        form.submitToPayment();
    }

    @Test(groups = {"forms", "negative"}, description = "Submitting empty required name keeps user on form")
    public void emptyRequiredNameKeepsFormVisible() {
        catalog.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        CheckoutActions form = catalog
                .openFirstProduct()
                .addToCart()
                .openCart()
                .proceedToCheckout()
                .enterAddressLine1("123 Automation Street")
                .enterCity("Bengaluru")
                .enterZip("560001")
                .enterCountry("India")
                .submitToPayment();

        new CheckoutValidator(form).assertRequiredNameValidationKeepsForm();
    }
}
