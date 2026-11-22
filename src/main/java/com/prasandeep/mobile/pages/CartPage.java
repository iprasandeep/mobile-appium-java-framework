package com.prasandeep.mobile.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private static final By PROCEED_TO_CHECKOUT = AppiumBy.accessibilityId("Proceed To Checkout button");

    public CheckoutFormPage proceedToCheckout() {
        tap(PROCEED_TO_CHECKOUT);
        return new CheckoutFormPage();
    }
}
