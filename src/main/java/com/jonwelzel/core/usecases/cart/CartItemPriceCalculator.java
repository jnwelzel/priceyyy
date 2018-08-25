package com.jonwelzel.core.usecases.cart;

import com.jonwelzel.core.gateways.BaseItemGateway;
import com.jonwelzel.core.model.BaseItem;
import com.jonwelzel.core.model.CartItem;

public class CartItemPriceCalculator {
    private BaseItemGateway baseItemGateway;

    public CartItemPriceCalculator(BaseItemGateway baseItemGateway) {
        this.baseItemGateway = baseItemGateway;
    }

    /**
     * 1. Use the {@link BaseItemGateway} to find the corresponding {@link BaseItem} object
     * 2. Perform the price calculation using the base price from the {@link BaseItem} and the {@link CartItem} data
     */
    public int calculatePrice(CartItem cartItem) {
        BaseItem baseItem = this.baseItemGateway.find(cartItem.getProductType(), cartItem.getOptions());

        return (baseItem.getBasePrice() + ((baseItem.getBasePrice() * cartItem.getArtistMarkup()) / 100)) * cartItem.getQuantity();
    }
}
