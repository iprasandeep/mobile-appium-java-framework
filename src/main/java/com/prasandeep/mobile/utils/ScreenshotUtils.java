package com.prasandeep.mobile.utils;

import com.prasandeep.mobile.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private static final Path SCREENSHOT_DIR = Paths.get("screenshots");

    private ScreenshotUtils() {
    }

    public static String capture(String testName) {
        try {
            Files.createDirectories(SCREENSHOT_DIR);
            String safeName = testName.replaceAll("[^a-zA-Z0-9-_]", "_");
            String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File dest = SCREENSHOT_DIR.resolve(safeName + "_" + stamp + ".png").toFile();
            File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, dest);
            return dest.getAbsolutePath();
        } catch (IOException | RuntimeException e) {
            return null;
        }
    }
}
