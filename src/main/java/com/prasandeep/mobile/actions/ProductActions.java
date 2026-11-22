package com.prasandeep.mobile.actions;

import com.prasandeep.mobile.base.BaseActions;
import com.prasandeep.mobile.locators.ProductLocators;

/** Click logic for product detail. */
public class ProductActions extends BaseActions {

    public ProductActions addToCart() {
        tap(ProductLocators.ADD_TO_CART);
        return this;
    }

    public CartActions openCart() {
        tap(ProductLocators.CART_BADGE);
        return new CartActions();
    }
}
