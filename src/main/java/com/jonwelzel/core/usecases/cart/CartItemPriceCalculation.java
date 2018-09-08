package com.jonwelzel.core.usecases.cart;

import com.jonwelzel.core.error.data.InvalidProductOptionsException;
import com.jonwelzel.core.error.data.RecordNotFoundException;
import com.jonwelzel.core.models.CartItem;

public interface CartItemPriceCalculation {
    int calculatePrice(CartItem cartItem) throws RecordNotFoundException, InvalidProductOptionsException;
}
