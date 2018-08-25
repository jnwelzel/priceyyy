package com.jonwelzel.core.models;

import java.util.List;
import java.util.Map;

public class BaseItem {
    private String productType;
    private List<Map.Entry<String, String>> options;
    private int basePrice;

    public BaseItem(String productType, List<Map.Entry<String, String>> options, int basePrice) {
        this.productType = productType;
        this.options = options;
        this.basePrice = basePrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<Map.Entry<String, String>> getOptions() {
        return options;
    }

    public void setOptions(List<Map.Entry<String, String>> options) {
        this.options = options;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
