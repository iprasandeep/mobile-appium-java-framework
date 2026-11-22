package com.prasandeep.mobile.utils;

import com.prasandeep.mobile.config.ConfigReader;
import com.prasandeep.mobile.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static WebDriverWait explicitWait() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(ConfigReader.getInt("explicit.wait.seconds")));
    }

    public static WebElement visible(By locator) {
        return explicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(By locator) {
        return explicitWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean invisible(By locator) {
        return explicitWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /** Short presence check for validators (avoids 20s false waits). */
    public static boolean isVisible(By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}

