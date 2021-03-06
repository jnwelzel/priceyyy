package com.jonwelzel.core.usecases.cart;

import com.jonwelzel.core.error.data.InvalidProductOptionsException;
import com.jonwelzel.core.error.data.RecordNotFoundException;
import com.jonwelzel.core.models.CartItem;
import com.jonwelzel.core.presenters.CartPricePresenter;

import java.util.List;

public class CartPriceCalculator {
    private CartItemPriceCalculation itemPriceCalculator;
    private CartPricePresenter presenter;

    public CartPriceCalculator(CartItemPriceCalculation itemPriceCalculator, CartPricePresenter presenter) {
        this.itemPriceCalculator = itemPriceCalculator;
        this.presenter = presenter;
    }

    public void calculatePrice(List<CartItem> cartItems) throws InvalidProductOptionsException {
        int sum = 0;
        for (CartItem item : cartItems) {
            try {
                sum += itemPriceCalculator.calculatePrice(item);
            } catch (RecordNotFoundException | ArithmeticException e) {
                presenter.presentError(e.getMessage());
                return;
            }
        }
        presenter.presentSuccess(sum);
    }
}
