package com.jonwelzel.core.models;

import java.util.Map;

public class CartItem {
    private String productType;
    private Map<String, String> options;
    private int artistMarkup;
    private int quantity;

    public CartItem(String productType, Map<String, String> options, int artistMarkup, int quantity) {
        this.productType = productType;
        this.options = options;
        this.artistMarkup = artistMarkup;
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public int getArtistMarkup() {
        return artistMarkup;
    }

    public void setArtistMarkup(int artistMarkup) {
        this.artistMarkup = artistMarkup;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
