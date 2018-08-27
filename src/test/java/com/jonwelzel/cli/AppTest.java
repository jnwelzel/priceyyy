package com.jonwelzel.cli;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

/**
 * Unit test for the App class.
 */
public class AppTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldPrintTheCartTotalToTheConsoleWithNewLineAtTheEnd() {
        String cartFilePath = getClass().getClassLoader().getResource("json/cart-11356.json").getPath();
        String basePricesFilePath = getClass().getClassLoader().getResource("json/base-prices.json").getPath();
        String expectedOutput = "11356\n";

        App.main(new String[]{cartFilePath, basePricesFilePath});

        Assertions.assertThat(systemOutRule.getLog()).isEqualTo(expectedOutput);
    }
}
