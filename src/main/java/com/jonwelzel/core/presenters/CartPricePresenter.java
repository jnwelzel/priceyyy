package com.jonwelzel.core.presenters;

public interface CartPricePresenter {
    void presentSuccess(int price);
    void presentError(String error);
}
