package com.jonwelzel.core.usecases.cart;

import com.jonwelzel.core.error.data.InvalidProductOptionsException;
import com.jonwelzel.core.gateways.baseitem.BaseItemGateway;
import com.jonwelzel.core.error.data.RecordNotFoundException;
import com.jonwelzel.core.models.BaseItem;
import com.jonwelzel.core.models.CartItem;

public class CartItemPriceCalculator implements CartItemPriceCalculation {
    private BaseItemGateway baseItemGateway;

    public CartItemPriceCalculator(BaseItemGateway baseItemGateway) {
        this.baseItemGateway = baseItemGateway;
    }

    /**
     * 1. Use the {@link BaseItemGateway} to find the corresponding {@link BaseItem} object
     * 2. Perform the price calculation using the base price from the {@link BaseItem} and the {@link CartItem} data
     */
    @Override
    public int calculatePrice(CartItem cartItem) throws RecordNotFoundException, InvalidProductOptionsException {
        BaseItem baseItem = this.baseItemGateway.find(cartItem.getProductType(), cartItem.getOptions());

        return (baseItem.getBasePrice() + ((baseItem.getBasePrice() * cartItem.getArtistMarkup()) / 100)) * cartItem.getQuantity();
    }
}
