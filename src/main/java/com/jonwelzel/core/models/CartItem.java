package com.jonwelzel.core.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CartItem {
    @JsonProperty("product-type")
    private String productType;

    private Map<String, String> options;

    @JsonProperty("artist-markup")
    private int artistMarkup;

    private int quantity;

    public CartItem() {
    }

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

    @JsonAnyGetter
    public Map<String, String> getOptions() {
        return options;
    }

    @JsonAnySetter
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
