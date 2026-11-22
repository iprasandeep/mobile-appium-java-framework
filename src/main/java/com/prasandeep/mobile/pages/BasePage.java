package com.prasandeep.mobile.pages;

import com.prasandeep.mobile.driver.DriverManager;
import com.prasandeep.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Shared page helpers for explicit waits and common actions.
 */
public abstract class BasePage {

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
        try {
            return WaitUtils.visible(locator).isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
