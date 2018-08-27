package com.jonwelzel.core.usecases.cart;

import com.jonwelzel.core.error.data.RecordNotFoundException;
import com.jonwelzel.core.models.CartItem;
import com.jonwelzel.core.presenters.CartPricePresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CartPriceCalculatorTest {
    private List<CartItem> cartItems;

    @Mock
    private CartItemPriceCalculation itemPriceCalculator;

    @Mock
    private CartPricePresenter presenter;

    @Before
    public void setUp() {
        CartItem cartItem1 = new CartItem("hoodie", null, 20, 5);
        CartItem cartItem2 = new CartItem("sticker", null, 20, 5);
        cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);


    }

    @Test
    public void theCartPriceShouldBeCalculatedAndTheResultPassedToThePresenter() throws RecordNotFoundException {
        given(itemPriceCalculator.calculatePrice(any(CartItem.class))).willReturn(10000);
        final int expectedPrice = 20000;

        new CartPriceCalculator(itemPriceCalculator, presenter).calculatePrice(cartItems);

        verify(presenter).presentSuccess(expectedPrice);
    }

    @Test
    public void whenExceptionIsThrownPresenterShouldPresentError() throws RecordNotFoundException {
        final String errorMessage = "Ayy lmao";
        given(itemPriceCalculator.calculatePrice(any(CartItem.class))).willThrow(new RecordNotFoundException(errorMessage));

        new CartPriceCalculator(itemPriceCalculator, presenter).calculatePrice(cartItems);

        verify(presenter).presentError(errorMessage);
    }
}
