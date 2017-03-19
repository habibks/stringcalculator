package net.habibksentini;


import net.habibksentini.stringcalculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @Before
    public void init() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void given_empty_string_then_return_0() {
        int result = stringCalculator.add("");
        assertThat(result, is(0));
    }

}
