package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for cart screen. */
public final class CartLocators {

    public static final By PROCEED_TO_CHECKOUT = AppiumBy.accessibilityId("Proceed To Checkout button");

    private CartLocators() {
    }
}
