package com.prasandeep.mobile.actions;

import com.prasandeep.mobile.base.BaseActions;
import com.prasandeep.mobile.locators.CartLocators;

/** Click logic for cart. */
public class CartActions extends BaseActions {

    public CheckoutActions proceedToCheckout() {
        tap(CartLocators.PROCEED_TO_CHECKOUT);
        return new CheckoutActions();
    }
}
