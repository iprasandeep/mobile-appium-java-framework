package com.prasandeep.mobile.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Checkout address form — covers form input and field-level error states.
 */
public class CheckoutFormPage extends BasePage {

    private static final By FULL_NAME = AppiumBy.accessibilityId("Full Name* input field");
    private static final By ADDRESS_LINE_1 = AppiumBy.accessibilityId("Address Line 1* input field");
    private static final By ADDRESS_LINE_2 = AppiumBy.accessibilityId("Address Line 2 input field");
    private static final By CITY = AppiumBy.accessibilityId("City* input field");
    private static final By STATE = AppiumBy.accessibilityId("State/Region input field");
    private static final By ZIP = AppiumBy.accessibilityId("Zip Code* input field");
    private static final By COUNTRY = AppiumBy.accessibilityId("Country* input field");
    private static final By TO_PAYMENT = AppiumBy.accessibilityId("To Payment button");
    private static final By FULL_NAME_ERROR = AppiumBy.xpath(
            "//*[contains(@text,'Full Name') and contains(@text,'required') "
                    + "or contains(@resource-id,'fullNameError') or contains(@text,'Please provide your full name')]");

    public CheckoutFormPage enterFullName(String value) {
        type(FULL_NAME, value);
        return this;
    }

    public CheckoutFormPage enterAddressLine1(String value) {
        type(ADDRESS_LINE_1, value);
        return this;
    }

    public CheckoutFormPage enterAddressLine2(String value) {
        type(ADDRESS_LINE_2, value);
        return this;
    }

    public CheckoutFormPage enterCity(String value) {
        type(CITY, value);
        return this;
    }

    public CheckoutFormPage enterState(String value) {
        type(STATE, value);
        return this;
    }

    public CheckoutFormPage enterZip(String value) {
        type(ZIP, value);
        return this;
    }

    public CheckoutFormPage enterCountry(String value) {
        type(COUNTRY, value);
        return this;
    }

    public CheckoutFormPage fillValidAddress() {
        return enterFullName("Prasandeep Tester")
                .enterAddressLine1("123 Automation Street")
                .enterAddressLine2("Suite 100")
                .enterCity("Bengaluru")
                .enterState("KA")
                .enterZip("560001")
                .enterCountry("India");
    }

    public CheckoutFormPage submitToPayment() {
        tap(TO_PAYMENT);
        return this;
    }

    public boolean isFullNameErrorVisible() {
        return isDisplayed(FULL_NAME_ERROR) || isDisplayed(FULL_NAME);
    }

    public boolean isFormDisplayed() {
        return isDisplayed(FULL_NAME) && isDisplayed(TO_PAYMENT);
    }
}
