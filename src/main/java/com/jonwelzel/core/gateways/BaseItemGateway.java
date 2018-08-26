package com.jonwelzel.core.gateways;

import com.jonwelzel.core.models.BaseItem;

import java.util.Map;
import java.util.Optional;

public interface BaseItemGateway {
    Optional<BaseItem> find(String productType, Map<String, String> options);
}
