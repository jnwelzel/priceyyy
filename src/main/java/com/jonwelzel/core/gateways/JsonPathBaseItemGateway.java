package com.jonwelzel.core.gateways;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.TypeRef;
import com.jonwelzel.core.models.BaseItem;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonPathBaseItemGateway implements BaseItemGateway {
    private ReadContext ctx;

    public JsonPathBaseItemGateway(File json) throws IOException {
        this.ctx = JsonPath.parse(json);
    }

    @Override
    public BaseItem find(String productType, Map<String, String> options) {
        final String query = String.format("$[?(@.product-type == '%s'%s)]", productType, getOptionsQueryString(options));
        TypeRef<List<BaseItem>> typeRef = new TypeRef<List<BaseItem>>() {};

        List<BaseItem> result = ctx.read(query, typeRef);

        return result.size() > 0 ? result.get(0) : null;
    }

    private String getOptionsQueryString(Map<String, String> options) {
        return options.entrySet().stream().map(
                entry -> String.format(" && '%s' in @.options.%s", entry.getValue(), entry.getKey())
        ).reduce("", String::concat);
    }
}
