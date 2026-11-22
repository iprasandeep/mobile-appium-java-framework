package com.prasandeep.mobile.driver;

import com.prasandeep.mobile.config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.SessionNotCreatedException;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

/**
 * Creates and holds a thread-local AndroidDriver session for Appium UiAutomator2.
 */
public final class DriverManager {

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void startDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        Path appPath = ConfigReader.resolveAppPath();
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(ConfigReader.get("platform.name"))
                .setAutomationName(ConfigReader.get("automation.name"))
                .setDeviceName(ConfigReader.get("device.name"))
                .setApp(appPath.toAbsolutePath().toString())
                .setAppPackage(ConfigReader.get("app.package"))
                .setAppActivity(ConfigReader.get("app.activity"))
                .setNoReset(ConfigReader.getBoolean("no.reset"))
                .setNewCommandTimeout(Duration.ofSeconds(ConfigReader.getInt("new.command.timeout.seconds")));

        ConfigReader.getOptional("device.udid").ifPresent(options::setUdid);
        ConfigReader.getOptional("platform.version").ifPresent(options::setPlatformVersion);

        String serverUrl = ConfigReader.get("appium.server.url");
        try {
            AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait.seconds")));
            DRIVER.set(driver);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Appium server URL: " + serverUrl, e);
        } catch (SessionNotCreatedException e) {
            throw new IllegalStateException(
                    "Could not create Appium session. Is Appium running at " + serverUrl
                            + " and is an emulator/device available?", e);
        }
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not started. Call DriverManager.startDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
