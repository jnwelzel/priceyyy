package com.jonwelzel.core.gateways.baseitem;

import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.TypeRef;
import com.jonwelzel.core.error.data.RecordNotFoundException;
import com.jonwelzel.core.models.BaseItem;

import java.util.*;
import java.util.stream.Collectors;

public class JsonPathBaseItemGateway implements BaseItemGateway {
    private ReadContext ctx;

    public JsonPathBaseItemGateway(ReadContext readContext) {
        this.ctx = readContext;
    }

    @Override
    public BaseItem find(String productType, Map<String, String> options) throws RecordNotFoundException {
        Map<String, String> filteredOptions = options.entrySet().stream()
                .filter(entry -> isOptionFromBasePriceList(entry.getKey(), getBasePriceListOptions(productType)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        final String query = String.format("$[?(@.product-type == '%s'%s)]", productType, buildQueryStringFromOptions(filteredOptions));
        TypeRef<List<BaseItem>> typeRef = new TypeRef<List<BaseItem>>() {};

        List<BaseItem> result = ctx.read(query, typeRef);

        if (result.size() == 0) {
            throw new RecordNotFoundException("Could not find record.");
        }

        return result.get(0);
    }

    private boolean isOptionFromBasePriceList(String optionKey, Set<String> basePriceListOptions) {
        return basePriceListOptions.contains(optionKey);
    }

    private Set<String> getBasePriceListOptions(String productType) {
        List<String> result = new ArrayList<>();
        final String query = String.format("$[?(@.product-type == '%s')]", productType);
        TypeRef<List<BaseItem>> typeRef = new TypeRef<List<BaseItem>>() {};
        return ctx.read(query, typeRef).get(0).getOptions().keySet();
    }

    private String buildQueryStringFromOptions(Map<String, String> options) {
        return options.entrySet().stream()
                .map(entry -> String.format(" && '%s' in @.options.%s", entry.getValue(), entry.getKey()))
                .reduce("", String::concat);
    }
}
