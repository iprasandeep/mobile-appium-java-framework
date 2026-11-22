package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for product detail screen. */
public final class ProductLocators {

    public static final By ADD_TO_CART = AppiumBy.accessibilityId("Add To Cart button");
    public static final By CART_BADGE = AppiumBy.accessibilityId("Displays number of items in your cart");

    private ProductLocators() {
    }
}
