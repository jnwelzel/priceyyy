package com.jonwelzel.core.usecases.cart;

import com.jonwelzel.core.gateways.BaseItemGateway;
import com.jonwelzel.core.models.BaseItem;
import com.jonwelzel.core.models.CartItem;

import java.util.Optional;

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
        Optional<BaseItem> baseItem = this.baseItemGateway.find(cartItem.getProductType(), cartItem.getOptions());

        return (baseItem.get().getBasePrice() + ((baseItem.get().getBasePrice() * cartItem.getArtistMarkup()) / 100)) * cartItem.getQuantity();
    }
}
