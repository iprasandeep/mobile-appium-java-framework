package com.prasandeep.mobile.validators;

import com.prasandeep.mobile.actions.CheckoutActions;
import org.testng.Assert;

/** Assertions for checkout form outcomes. */
public class CheckoutValidator {

    private final CheckoutActions checkout;

    public CheckoutValidator(CheckoutActions checkout) {
        this.checkout = checkout;
    }

    public void assertFormDisplayed() {
        Assert.assertTrue(checkout.isFormDisplayed(),
                "Checkout address form should be displayed");
    }

    public void assertFormStillVisibleAfterFill() {
        Assert.assertTrue(checkout.isFormDisplayed(),
                "Form should still be visible after filling fields");
    }

    public void assertRequiredNameValidationKeepsForm() {
        Assert.assertTrue(
                checkout.isFormDisplayed() || checkout.isFullNameErrorVisible(),
                "Empty required full name should keep checkout form / show validation");
    }
}
