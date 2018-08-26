package com.jonwelzel.core.usecases.cart;


import com.jonwelzel.core.gateways.BaseItemGateway;
import com.jonwelzel.core.models.BaseItem;
import com.jonwelzel.core.models.CartItem;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CartItemPriceCalculatorTest {

    @Mock
    private BaseItemGateway baseItemGateway;

    private CartItem defaultCartItem;
    private BaseItem defaultBaseItem;

    @Before
    public void setUp() {
        defaultCartItem = new CartItem("hoodie", null, 20, 5);
        defaultBaseItem = new BaseItem("hoodie", null, 4500);
    }

    @Test
    public void thePriceForTheItemShouldBeCalculated() {
        given(baseItemGateway.find(defaultCartItem.getProductType(), defaultCartItem.getOptions())).willReturn(java.util.Optional.ofNullable(defaultBaseItem));
        final int expectedPrice = 27000;

        final int result = new CartItemPriceCalculator(baseItemGateway).calculatePrice(defaultCartItem);

        Assertions.assertThat(result).isEqualTo(expectedPrice);
    }
}
