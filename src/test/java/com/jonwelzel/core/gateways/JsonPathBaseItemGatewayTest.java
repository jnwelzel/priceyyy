package com.jonwelzel.core.gateways;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import com.jonwelzel.core.models.BaseItem;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class JsonPathBaseItemGatewayTest {
    private File json;
    private JsonPathBaseItemGateway gateway;

    @BeforeClass
    public static void init() {
        Configuration.setDefaults(new Configuration.Defaults() {
            private final JsonProvider jsonProvider = new JacksonJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();
            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }
        });
    }

    @Before
    public void setUp() {
        json = new File(getClass().getClassLoader().getResource("json/base-prices.json").getFile());
    }

    @Test
    public void shouldFindTheCorrectItem() throws IOException {
        gateway = new JsonPathBaseItemGateway(json);
        Map<String, String> options = new HashMap<>();
        options.put("size", "small");
        options.put("colour", "white");
        final int expectedBasePrice = 3800;

        BaseItem result = gateway.find("hoodie", options);

        Assertions.assertThat(result.getBasePrice()).isEqualTo(expectedBasePrice);
    }
}
