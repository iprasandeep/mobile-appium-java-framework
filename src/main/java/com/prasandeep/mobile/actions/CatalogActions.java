package com.prasandeep.mobile.actions;

import com.prasandeep.mobile.base.BaseActions;
import com.prasandeep.mobile.locators.CatalogLocators;

/** Click / navigation logic for catalog screen. */
public class CatalogActions extends BaseActions {

    public CatalogActions openMenu() {
        tap(CatalogLocators.MENU_BUTTON);
        return this;
    }

    public LoginActions openLoginFromMenu() {
        openMenu();
        // Menu list is long — scroll until Login is visible, then tap.
        try {
            driver().findElement(io.appium.java_client.AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))"
                            + ".scrollIntoView(new UiSelector().description(\"Login Menu Item\"))"));
        } catch (RuntimeException ignored) {
            // Already on screen.
        }
        tap(CatalogLocators.LOGIN_MENU_ITEM);
        return new LoginActions();
    }

    public ProductActions openFirstProduct() {
        tap(CatalogLocators.FIRST_PRODUCT);
        return new ProductActions();
    }

    public boolean isProductsVisible() {
        return isDisplayed(CatalogLocators.CATALOG_TITLE);
    }

    public boolean isLoggedInMenuVisible() {
        openMenu();
        return isDisplayed(CatalogLocators.LOGOUT_MENU_ITEM);
    }
}
