package com.mct.appdirect.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class URLValidatorTest {

    private final URLValidator urlValidator = new URLValidator();

    @Test
    public void shouldReturnTrueIfURLIsValid() {
        assertThat(urlValidator.isValid("http://www.appdirect.com"), equalTo(true));
    }

    @Test
    public void shouldReturnFalseIfURLIsInvalid() {
        assertThat(urlValidator.isValid("notAnUrl"), equalTo(false));
    }
}