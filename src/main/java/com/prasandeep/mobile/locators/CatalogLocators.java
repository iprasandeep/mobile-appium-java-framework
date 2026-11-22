package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for catalog / home screen. */
public final class CatalogLocators {

    public static final By MENU_BUTTON = AppiumBy.accessibilityId("View menu");
    public static final By LOGIN_MENU_ITEM = AppiumBy.accessibilityId("Login Menu Item");
    public static final By LOGOUT_MENU_ITEM = AppiumBy.accessibilityId("Logout Menu Item");
    public static final By CATALOG_TITLE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    public static final By FIRST_PRODUCT = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\").instance(0)");

    private CatalogLocators() {
    }
}
