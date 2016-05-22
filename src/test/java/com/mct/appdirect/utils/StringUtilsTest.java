package com.mct.appdirect.utils;

import org.junit.Test;

import static com.mct.appdirect.utils.StringUtils.isNumeric;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StringUtilsTest {

    @Test
    public void isNumericShouldReturnOptionalOf1For1() {
        assertThat(isNumeric("1"), equalTo(of(1)));
    }

    @Test
    public void isNumericShouldReturnEmptyForA() {
        assertThat(isNumeric("a"), is(empty()));
    }
}