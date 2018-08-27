package com.jonwelzel.core.gateways.baseitem;

import com.jonwelzel.core.error.data.RecordNotFoundException;
import com.jonwelzel.core.models.BaseItem;

import java.util.Map;

public interface BaseItemGateway {
    BaseItem find(String productType, Map<String, String> options) throws RecordNotFoundException;
}
