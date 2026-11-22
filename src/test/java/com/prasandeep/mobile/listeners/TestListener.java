package com.prasandeep.mobile.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.prasandeep.mobile.driver.DriverManager;
import com.prasandeep.mobile.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Dual reporting: Extent HTML under reports/ and Allure raw results under target/allure-results.
 */
public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        Path reportPath = Paths.get("reports", "extent-report.html");
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath.toFile());
        spark.config().setDocumentTitle("Mobile Appium Framework");
        spark.config().setReportName("Android UI Automation");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Framework", "Appium + Java + TestNG + POM");
        extent.setSystemInfo("Author", "prasandeep");
    }

    @Override
    public void onTestStart(ITestResult result) {
        TEST.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().log(Status.PASS, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String path = ScreenshotUtils.capture(result.getMethod().getMethodName());
        TEST.get().log(Status.FAIL, result.getThrowable());
        if (path != null) {
            TEST.get().addScreenCaptureFromPath(path);
        }
        attachAllureScreenshot(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().log(Status.SKIP, "Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
        TEST.remove();
    }

    private void attachAllureScreenshot(String name) {
        try {
            byte[] bytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name + "-failure", "image/png", new ByteArrayInputStream(bytes), ".png");
        } catch (RuntimeException ignored) {
            // Driver may already be gone; skip attachment.
        }
    }
}
