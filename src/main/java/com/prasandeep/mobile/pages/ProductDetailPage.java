package com.prasandeep.mobile.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Product detail — add to cart path used before checkout form.
 */
public class ProductDetailPage extends BasePage {

    private static final By ADD_TO_CART = AppiumBy.accessibilityId("Add To Cart button");
    private static final By CART_BADGE = AppiumBy.accessibilityId("Displays number of items in your cart");

    public ProductDetailPage addToCart() {
        tap(ADD_TO_CART);
        return this;
    }

    public CartPage openCart() {
        tap(CART_BADGE);
        return new CartPage();
    }
}
