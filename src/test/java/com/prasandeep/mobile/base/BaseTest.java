package com.prasandeep.mobile.base;

import com.prasandeep.mobile.driver.DriverManager;
import com.prasandeep.mobile.listeners.TestListener;
import com.prasandeep.mobile.pages.CatalogPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected CatalogPage catalogPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.startDriver();
        catalogPage = new CatalogPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
