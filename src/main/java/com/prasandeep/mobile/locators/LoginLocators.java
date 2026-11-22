package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for login screen (My Demo App 2.2.0). */
public final class LoginLocators {

    public static final By USERNAME = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    public static final By PASSWORD = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    public static final By LOGIN_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn");
    public static final By ERROR_MESSAGE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/errorTV");
    public static final By LOGIN_TITLE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginTV");

    private LoginLocators() {
    }
}
