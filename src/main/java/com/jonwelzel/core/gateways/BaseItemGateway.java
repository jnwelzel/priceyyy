package com.jonwelzel.core.gateways;

import com.jonwelzel.core.model.BaseItem;

import java.util.List;
import java.util.Map;

public interface BaseItemGateway {
    BaseItem find(String productType, List<Map.Entry<String, String>> options);
}
