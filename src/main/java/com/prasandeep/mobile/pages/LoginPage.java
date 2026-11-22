package com.prasandeep.mobile.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Login screen — username, password, submit, and error messaging.
 * Locators verified against Sauce Labs My Demo App Android 2.2.0.
 */
public class LoginPage extends BasePage {

    private static final By USERNAME = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    private static final By PASSWORD = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private static final By LOGIN_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn");
    private static final By ERROR_MESSAGE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/errorTV");
    private static final By LOGIN_TITLE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginTV");

    public LoginPage enterUsername(String username) {
        type(USERNAME, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD, password);
        return this;
    }

    public CatalogPage submitValidLogin() {
        tap(LOGIN_BUTTON);
        return new CatalogPage();
    }

    public LoginPage submitInvalidLogin() {
        tap(LOGIN_BUTTON);
        return this;
    }

    public CatalogPage loginAs(String username, String password) {
        return enterUsername(username).enterPassword(password).submitValidLogin();
    }

    public String getErrorMessage() {
        return textOf(ERROR_MESSAGE);
    }

    public boolean isErrorVisible() {
        return isDisplayed(ERROR_MESSAGE);
    }

    public boolean isLoginScreenDisplayed() {
        return isDisplayed(LOGIN_TITLE) || isDisplayed(USERNAME);
    }
}
