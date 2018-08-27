package com.jonwelzel.cli;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class CliCartPricePresenterTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldPresentSuccess() {
        String expectedResult = "12500\n";

        new CliCartPricePresenter().presentSuccess(12500);

        Assertions.assertThat(systemOutRule.getLog()).isEqualTo(expectedResult);
    }

    @Test
    public void shouldPresentError() {
        String expectedResult = "Oops!\n";

        new CliCartPricePresenter().presentError("Oops!");

        Assertions.assertThat(systemOutRule.getLog()).isEqualTo(expectedResult);
    }
}
