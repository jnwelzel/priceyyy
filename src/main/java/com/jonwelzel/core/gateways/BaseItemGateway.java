package com.jonwelzel.core.gateways;

import com.jonwelzel.core.models.BaseItem;

import java.util.Map;

public interface BaseItemGateway {
    BaseItem find(String productType, Map<String, String> options);
}
