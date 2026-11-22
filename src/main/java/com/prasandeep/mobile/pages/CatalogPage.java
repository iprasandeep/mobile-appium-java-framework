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
    private static final By CATALOG_TITLE = AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")");
    private static final By FIRST_PRODUCT = AppiumBy.accessibilityId("Product Item");

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
        boolean loggedIn = isDisplayed(LOGOUT_MENU_ITEM);
        // Close drawer by tapping menu again if needed — logout item visibility is enough signal.
        return loggedIn;
    }

    public ProductDetailPage openFirstProduct() {
        tap(FIRST_PRODUCT);
        return new ProductDetailPage();
    }
}
