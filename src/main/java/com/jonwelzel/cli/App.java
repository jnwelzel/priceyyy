package com.jonwelzel.cli;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import com.jonwelzel.core.models.CartItem;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Simple main application
 */
public class App {

    public static void main(String[] args) {
        initJsonPath();
        String cartFilePath;
        String basePricesFilePath;

        if (args.length == 0) {
            Scanner terminalInput = new Scanner(System.in);

            System.out.print("Please type the cart file path: ");
            cartFilePath = terminalInput.nextLine();

            System.out.print("Now the base prices list file path: ");
            basePricesFilePath = terminalInput.nextLine();
        } else {
            cartFilePath = args[0];
            basePricesFilePath = args[1];
        }

        try {
            new CartPriceCalculatorBuilder(basePricesFilePath).build().calculatePrice(parseCartItems(cartFilePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<CartItem> parseCartItems(String filePath) throws IOException {
        ObjectMapper om = new ObjectMapper();

        return om.readValue(new File(filePath), new TypeReference<List<CartItem>>(){});
    }

    private static void initJsonPath() {
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

}
