package com.jonwelzel.cli.builders;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jonwelzel.cli.CliCartPricePresenter;
import com.jonwelzel.core.gateways.baseitem.JsonPathBaseItemGateway;
import com.jonwelzel.core.usecases.cart.CartItemPriceCalculator;
import com.jonwelzel.core.usecases.cart.CartPriceCalculator;

import java.io.File;
import java.io.IOException;

public class CartPriceCalculatorBuilder {
    private String basePricesFile;

    public CartPriceCalculatorBuilder(String basePricesFile) {
        this.basePricesFile = basePricesFile;
    }

    public CartPriceCalculator build() throws IOException {
        File basePricesFile = new File(this.basePricesFile);
        ReadContext basePriceReadContext = JsonPath.parse(basePricesFile);
        JsonPathBaseItemGateway baseItemGateway = new JsonPathBaseItemGateway(basePriceReadContext);
        CartItemPriceCalculator cartItemPriceCalculator = new CartItemPriceCalculator(baseItemGateway);

        return new CartPriceCalculator(cartItemPriceCalculator, new CliCartPricePresenter());
    }
}
