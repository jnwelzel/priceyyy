package com.jonwelzel.core.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;
import java.util.Map;

public class BaseItem {
    private String productType;
    private Map<String, List<String>> options;
    private int basePrice;

    public BaseItem() {
    }

    public BaseItem(String productType, Map<String, List<String>> options, int basePrice) {
        this.productType = productType;
        this.options = options;
        this.basePrice = basePrice;
    }

    @JsonGetter("product-type")
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonAnyGetter
    public Map<String, List<String>> getOptions() {
        return options;
    }

    @JsonAnySetter
    public void setOptions(Map<String, List<String>> options) {
        this.options = options;
    }

    @JsonGetter("base-price")
    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
