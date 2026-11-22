package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for cart screen (My Demo App 2.2.0). */
public final class CartLocators {

    /** Button text is "Proceed To Checkout"; content-desc is the confirms string. */
    public static final By PROCEED_TO_CHECKOUT = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Proceed To Checkout\")");

    private CartLocators() {
    }
}
