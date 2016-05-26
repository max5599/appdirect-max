package com.mct.appdirect.utils;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.util.NestedServletException;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public final class AssertNestedServetException {

    private AssertNestedServetException() {
    }

    @FunctionalInterface
    public interface MvcPerform {
        void perform(RequestBuilder requestBuilder) throws Exception;
    }

    public static void assertPerformRequestBuilderThrowRootCause(MvcPerform mvcPerform, RequestBuilder requestBuilder, Throwable expectedRootCause) throws Exception {
        try {
            mvcPerform.perform(requestBuilder);
            fail("Excepted a NestedServletException to be thrown");
        } catch (NestedServletException e) {
            Throwable rootCause = e.getRootCause();
            assertThat(rootCause, instanceOf(expectedRootCause.getClass()));
            assertThat(rootCause.getMessage(), is(expectedRootCause.getMessage()));
        }
    }
}
