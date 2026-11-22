package com.prasandeep.mobile.actions;

import com.prasandeep.mobile.base.BaseActions;
import com.prasandeep.mobile.locators.CheckoutLocators;

/** Form fill / click logic for checkout address. */
public class CheckoutActions extends BaseActions {

    public CheckoutActions enterFullName(String value) {
        type(CheckoutLocators.FULL_NAME, value);
        return this;
    }

    public CheckoutActions enterAddressLine1(String value) {
        type(CheckoutLocators.ADDRESS_LINE_1, value);
        return this;
    }

    public CheckoutActions enterAddressLine2(String value) {
        type(CheckoutLocators.ADDRESS_LINE_2, value);
        return this;
    }

    public CheckoutActions enterCity(String value) {
        type(CheckoutLocators.CITY, value);
        return this;
    }

    public CheckoutActions enterState(String value) {
        type(CheckoutLocators.STATE, value);
        return this;
    }

    public CheckoutActions enterZip(String value) {
        type(CheckoutLocators.ZIP, value);
        return this;
    }

    public CheckoutActions enterCountry(String value) {
        type(CheckoutLocators.COUNTRY, value);
        return this;
    }

    public CheckoutActions fillValidAddress() {
        return enterFullName("Prasandeep Tester")
                .enterAddressLine1("123 Automation Street")
                .enterAddressLine2("Suite 100")
                .enterCity("Bengaluru")
                .enterState("KA")
                .enterZip("560001")
                .enterCountry("India");
    }

    public CheckoutActions submitToPayment() {
        tap(CheckoutLocators.TO_PAYMENT);
        return this;
    }

    public boolean isFormDisplayed() {
        return isDisplayed(CheckoutLocators.FULL_NAME) && isDisplayed(CheckoutLocators.TO_PAYMENT);
    }

    public boolean isFullNameErrorVisible() {
        return isDisplayed(CheckoutLocators.FULL_NAME_ERROR) || isDisplayed(CheckoutLocators.FULL_NAME);
    }
}
