package com.prasandeep.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/** Locators for checkout address form. */
public final class CheckoutLocators {

    public static final By FULL_NAME = AppiumBy.accessibilityId("Full Name* input field");
    public static final By ADDRESS_LINE_1 = AppiumBy.accessibilityId("Address Line 1* input field");
    public static final By ADDRESS_LINE_2 = AppiumBy.accessibilityId("Address Line 2 input field");
    public static final By CITY = AppiumBy.accessibilityId("City* input field");
    public static final By STATE = AppiumBy.accessibilityId("State/Region input field");
    public static final By ZIP = AppiumBy.accessibilityId("Zip Code* input field");
    public static final By COUNTRY = AppiumBy.accessibilityId("Country* input field");
    public static final By TO_PAYMENT = AppiumBy.accessibilityId("To Payment button");
    public static final By FULL_NAME_ERROR = AppiumBy.xpath(
            "//*[contains(@text,'Full Name') and contains(@text,'required') "
                    + "or contains(@resource-id,'fullNameError') or contains(@text,'Please provide your full name')]");

    private CheckoutLocators() {
    }
}
