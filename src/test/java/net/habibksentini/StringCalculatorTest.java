package net.habibksentini;


import net.habibksentini.stringcalculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

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

    @Test
    public void given_1_then_return_1() {
        int result = stringCalculator.add("1");
        assertThat(result, is(1));
    }

    @Test
    public void given_1_2_then_return_3() {
        int result = stringCalculator.add("1,2");
        assertThat(result, is(3));
    }

    @Test
    public void given_amount_of_numbers_then_return_their_sum() {
        int result = stringCalculator.add("3,8,9");
        assertThat(result, is(20));
    }

    @Test
    public void given_amount_of_numbers_with_new_lines_between_numbers_then_return_their_sum() {
        int result = stringCalculator.add("1\n2,3\n8");
        assertThat(result, is(14));
    }

    @Test
    public void given_delimiter_and_amount_of_numbers_then_return_their_sum() {
        int result = stringCalculator.add("//;\n1;2");
        assertThat(result, is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_amount_of_numbers_with_a_negative_numbers_then_throw_exception_with_all_of_them_in_the_message() {
        try {
            stringCalculator.add("1,-2,4,-1");
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("negatives not allowed: -2 -1"));
            throw e;
        }
    }

    @Test
    public void given_amount_of_numbers_with_number_bigger_then_1000_then_ignore_this_number_and_return_the_sum() {
        int result = stringCalculator.add("1000,2,4,1");
        assertThat(result, is(7));
    }


}
