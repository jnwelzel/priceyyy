package com.jonwelzel.core.gateways;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import com.jonwelzel.core.error.data.InvalidProductOptionsException;
import com.jonwelzel.core.error.data.RecordNotFoundException;
import com.jonwelzel.core.gateways.baseitem.JsonPathBaseItemGateway;
import com.jonwelzel.core.models.BaseItem;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
    private JsonPathBaseItemGateway gateway;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void setUp() throws IOException {
        File json = new File(getClass().getClassLoader().getResource("json/base-prices.json").getFile());
        gateway = new JsonPathBaseItemGateway(JsonPath.parse(json));
    }

    @Test
    public void shouldFindTheCorrectItem() throws RecordNotFoundException, InvalidProductOptionsException {
        Map<String, String> options = new HashMap<>();
        options.put("size", "small");
        options.put("colour", "white");
        final int expectedBasePrice = 3800;

        BaseItem result = gateway.find("hoodie", options);

        Assertions.assertThat(result.getBasePrice()).isEqualTo(expectedBasePrice);
    }

    @Test
    public void shouldIgnoreOptionsThatAreNotInTheBasePriceListForThatProduct() throws RecordNotFoundException, InvalidProductOptionsException {
        Map<String, String> options = new HashMap<>();
        options.put("size", "small");
        options.put("colour", "white");
        options.put("print-location", "front");
        final int expectedBasePrice = 3800;

        BaseItem result = gateway.find("hoodie", options);

        Assertions.assertThat(result.getBasePrice()).isEqualTo(expectedBasePrice);
    }

    @Test
    public void shouldThrowRecordNotFoundExceptionWhenProductOptionsAreInvalid() throws RecordNotFoundException, InvalidProductOptionsException {
        Map<String, String> options = new HashMap<>();
        options.put("size", "small");
        options.put("colour", "pink");
        options.put("print-location", "front");
        thrown.expect(RecordNotFoundException.class);

        gateway.find("hoodie", options);
    }

    @Test
    public void shouldThrowInvalidProductOptionsExceptionWhenProductTypeIsInvalid() throws RecordNotFoundException, InvalidProductOptionsException {
        Map<String, String> options = new HashMap<>();
        options.put("size", "small");
        options.put("colour", "white");
        options.put("print-location", "front");
        thrown.expect(InvalidProductOptionsException.class);

        gateway.find("ayy lmao", options);
    }
}
