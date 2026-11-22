package com.prasandeep.mobile.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Login screen — username, password, submit, and error messaging.
 */
public class LoginPage extends BasePage {

    private static final By USERNAME = AppiumBy.accessibilityId("Username input field");
    private static final By PASSWORD = AppiumBy.accessibilityId("Password input field");
    private static final By LOGIN_BUTTON = AppiumBy.accessibilityId("Login button");
    private static final By ERROR_MESSAGE = AppiumBy.xpath(
            "//*[contains(@text,'provided credentials do not match') or contains(@text,'Password') "
                    + "or contains(@resource-id,'errorTV') or contains(@resource-id,'tvError')]");
    private static final By LOGIN_SCREEN_MARKER = AppiumBy.androidUIAutomator(
            "new UiSelector().textContains(\"Login\")");

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
        return isDisplayed(LOGIN_SCREEN_MARKER) || isDisplayed(USERNAME);
    }
}
