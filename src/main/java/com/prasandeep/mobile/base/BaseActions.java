package com.prasandeep.mobile.base;

import com.prasandeep.mobile.driver.DriverManager;
import com.prasandeep.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Shared interaction helpers (tap / type / read). Locators stay in locator classes;
 * screen flows live in *Actions; assertions live in *Validator.
 */
public abstract class BaseActions {

    protected AndroidDriver driver() {
        return DriverManager.getDriver();
    }

    protected WebElement findVisible(By locator) {
        return WaitUtils.visible(locator);
    }

    protected void tap(By locator) {
        WaitUtils.clickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = WaitUtils.visible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String textOf(By locator) {
        return WaitUtils.visible(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        return WaitUtils.isVisible(locator, 5);
    }

    protected void hideKeyboardQuietly() {
        try {
            driver().hideKeyboard();
        } catch (RuntimeException ignored) {
            // Keyboard may already be hidden.
        }
    }
}

