package com.jonwelzel.core.usecases.cart;

import com.jonwelzel.core.models.CartItem;
import com.jonwelzel.core.presenters.CartPricePresenter;

import java.util.List;

public class CartPriceCalculator {
    private CartItemPriceCalculator itemPriceCalculator;
    private CartPricePresenter cartPricePresenter;

    public CartPriceCalculator(CartItemPriceCalculator itemPriceCalculator, CartPricePresenter cartPricePresenter) {
        this.itemPriceCalculator = itemPriceCalculator;
        this.cartPricePresenter = cartPricePresenter;
    }

    public void calculatePrice(List<CartItem> cartItems) {
        try {
            int total = cartItems.stream().mapToInt(item -> itemPriceCalculator.calculatePrice(item)).sum();
            cartPricePresenter.presentSuccess(total);
        } catch (Exception e) {
            cartPricePresenter.presentError(e.getMessage());
        }
    }
}
