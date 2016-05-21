package com.mct.appdirect.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class URLValidatorTest {

    private final URLValidator urlValidator = new URLValidator();

    @Test
    public void shouldReturnInvalidFalseIfURLIsValid() {
        assertThat(urlValidator.isInvalid("http://www.appdirect.com"), equalTo(false));
    }

    @Test
    public void shouldReturnInvalidTrueIfURLIsInvalid() {
        assertThat(urlValidator.isInvalid("notAnUrl"), equalTo(true));
    }
}