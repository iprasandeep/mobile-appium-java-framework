package com.prasandeep.mobile.base;

import com.prasandeep.mobile.actions.CartActions;
import com.prasandeep.mobile.actions.CatalogActions;
import com.prasandeep.mobile.actions.CheckoutActions;
import com.prasandeep.mobile.actions.LoginActions;
import com.prasandeep.mobile.actions.ProductActions;
import com.prasandeep.mobile.driver.DriverManager;
import com.prasandeep.mobile.listeners.TestListener;
import com.prasandeep.mobile.validators.CatalogValidator;
import com.prasandeep.mobile.validators.CheckoutValidator;
import com.prasandeep.mobile.validators.LoginValidator;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class, TestListener.class})
public abstract class BaseTest {

    protected CatalogActions catalog;
    protected LoginActions login;
    protected ProductActions product;
    protected CartActions cart;
    protected CheckoutActions checkout;

    protected CatalogValidator catalogValidator;
    protected LoginValidator loginValidator;
    protected CheckoutValidator checkoutValidator;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.startDriver();
        catalog = new CatalogActions();
        login = new LoginActions();
        product = new ProductActions();
        cart = new CartActions();
        checkout = new CheckoutActions();
        catalogValidator = new CatalogValidator(catalog);
        loginValidator = new LoginValidator(login);
        checkoutValidator = new CheckoutValidator(checkout);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
