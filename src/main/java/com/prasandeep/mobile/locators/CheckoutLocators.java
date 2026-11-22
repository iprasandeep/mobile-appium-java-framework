package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for checkout address form (My Demo App 2.2.0). */
public final class CheckoutLocators {

    public static final By FULL_NAME = AppiumBy.id("com.saucelabs.mydemoapp.android:id/fullNameET");
    public static final By ADDRESS_LINE_1 = AppiumBy.id("com.saucelabs.mydemoapp.android:id/address1ET");
    public static final By ADDRESS_LINE_2 = AppiumBy.id("com.saucelabs.mydemoapp.android:id/address2ET");
    public static final By CITY = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cityET");
    public static final By STATE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/stateET");
    public static final By ZIP = AppiumBy.id("com.saucelabs.mydemoapp.android:id/zipET");
    public static final By COUNTRY = AppiumBy.id("com.saucelabs.mydemoapp.android:id/countryET");
    public static final By TO_PAYMENT = AppiumBy.accessibilityId("Saves user info for checkout");
    public static final By FULL_NAME_ERROR = AppiumBy.xpath(
            "//*[contains(@text,'Full Name') or contains(@text,'provide your full name') "
                    + "or contains(@resource-id,'fullNameError')]");

    private CheckoutLocators() {
    }
}
