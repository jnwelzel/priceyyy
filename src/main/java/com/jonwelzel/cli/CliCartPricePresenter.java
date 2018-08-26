package com.jonwelzel.cli;

import com.jonwelzel.core.presenters.CartPricePresenter;

public class CliCartPricePresenter implements CartPricePresenter {
    @Override
    public void presentSuccess(int price) {
        System.out.println(price);
    }

    @Override
    public void presentError(String error) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("ERROR\n" + error);
    }
}
