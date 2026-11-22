package com.prasandeep.mobile.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Product catalog / home screen of Sauce Labs My Demo App Android.
 */
public class CatalogPage extends BasePage {

    private static final By MENU_BUTTON = AppiumBy.accessibilityId("View menu");
    private static final By LOGIN_MENU_ITEM = AppiumBy.accessibilityId("Login Menu Item");
    private static final By LOGOUT_MENU_ITEM = AppiumBy.accessibilityId("Logout Menu Item");
    private static final By CATALOG_TITLE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private static final By FIRST_PRODUCT = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\").instance(0)");

    public CatalogPage openMenu() {
        tap(MENU_BUTTON);
        return this;
    }

    public LoginPage openLoginFromMenu() {
        openMenu();
        tap(LOGIN_MENU_ITEM);
        return new LoginPage();
    }

    public boolean isProductsVisible() {
        return isDisplayed(CATALOG_TITLE);
    }

    public boolean isLoggedIn() {
        openMenu();
        return isDisplayed(LOGOUT_MENU_ITEM);
    }

    public ProductDetailPage openFirstProduct() {
        tap(FIRST_PRODUCT);
        return new ProductDetailPage();
    }
}
