package com.prasandeep.mobile.actions;

import com.prasandeep.mobile.base.BaseActions;
import com.prasandeep.mobile.locators.LoginLocators;

/** Click / type logic for login screen. */
public class LoginActions extends BaseActions {

    public LoginActions enterUsername(String username) {
        type(LoginLocators.USERNAME, username);
        return this;
    }

    public LoginActions enterPassword(String password) {
        type(LoginLocators.PASSWORD, password);
        return this;
    }

    public CatalogActions submitLogin() {
        tap(LoginLocators.LOGIN_BUTTON);
        return new CatalogActions();
    }

    public LoginActions submitLoginExpectingError() {
        tap(LoginLocators.LOGIN_BUTTON);
        return this;
    }

    public CatalogActions loginAs(String username, String password) {
        return enterUsername(username).enterPassword(password).submitLogin();
    }

    public String readErrorMessage() {
        return textOf(LoginLocators.ERROR_MESSAGE);
    }

    public boolean isErrorVisible() {
        return isDisplayed(LoginLocators.ERROR_MESSAGE);
    }

    public boolean isLoginScreenDisplayed() {
        return isDisplayed(LoginLocators.LOGIN_TITLE) || isDisplayed(LoginLocators.USERNAME);
    }
}
