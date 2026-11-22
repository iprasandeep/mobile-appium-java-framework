package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for product detail screen (My Demo App 2.2.0). */
public final class ProductLocators {

    public static final By ADD_TO_CART = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt");
    public static final By CART_BADGE = AppiumBy.accessibilityId("View cart");

    private ProductLocators() {
    }
}
