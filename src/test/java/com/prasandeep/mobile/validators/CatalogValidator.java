package com.prasandeep.mobile.validators;

import com.prasandeep.mobile.actions.CatalogActions;
import org.testng.Assert;

/** Assertions for catalog / home outcomes. */
public class CatalogValidator {

    private final CatalogActions catalog;

    public CatalogValidator(CatalogActions catalog) {
        this.catalog = catalog;
    }

    public void assertProductsVisible() {
        Assert.assertTrue(catalog.isProductsVisible(),
                "Products catalog should be visible");
    }
}
