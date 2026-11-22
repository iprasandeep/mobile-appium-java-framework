package com.prasandeep.mobile.tests;

import com.prasandeep.mobile.base.BaseTest;
import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.pages.CheckoutFormPage;
import com.prasandeep.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormInputTests extends BaseTest {

    @Test(groups = {"forms", "smoke"}, description = "Logged-in user can reach checkout address form")
    public void checkoutAddressFormIsReachable() {
        LoginPage loginPage = catalogPage.openLoginFromMenu();
        loginPage.loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        CheckoutFormPage form = catalogPage
                .openFirstProduct()
                .addToCart()
                .openCart()
                .proceedToCheckout();

        Assert.assertTrue(form.isFormDisplayed(), "Checkout address form should be displayed");
    }

    @Test(groups = {"forms"}, description = "Valid address fields can be filled on checkout form")
    public void fillValidCheckoutAddressFields() {
        catalogPage.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        CheckoutFormPage form = catalogPage
                .openFirstProduct()
                .addToCart()
                .openCart()
                .proceedToCheckout()
                .fillValidAddress();

        Assert.assertTrue(form.isFormDisplayed(), "Form should still be visible after filling fields");
        form.submitToPayment();
    }

    @Test(groups = {"forms", "negative"}, description = "Submitting empty required name keeps user on form")
    public void emptyRequiredNameKeepsFormVisible() {
        catalogPage.openLoginFromMenu()
                .loginAs(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        CheckoutFormPage form = catalogPage
                .openFirstProduct()
                .addToCart()
                .openCart()
                .proceedToCheckout()
                .enterAddressLine1("123 Automation Street")
                .enterCity("Bengaluru")
                .enterZip("560001")
                .enterCountry("India")
                .submitToPayment();

        Assert.assertTrue(form.isFormDisplayed() || form.isFullNameErrorVisible(),
                "Empty required full name should keep checkout form / show validation");
    }
}
