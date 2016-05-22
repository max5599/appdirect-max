package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.utils.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.mct.appdirect.response.BaseResponseBuilder.aSuccessfulResponse;
import static com.mct.appdirect.utils.AssertNestedServetException.assertPerformRequestBuilderThrowRootCause;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CancelNotificationControllerTest {

    private static final String SUBSCRIPTION_CANCEL_URL = "/subscription/cancel";

    @Test
    public void shouldReturnOkWithTheResponse() throws Exception {
        MockMvc mvc = createMockMvcWithAlwaysValidURL();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CANCEL_URL)
                .accept(MediaType.APPLICATION_JSON)
                .param("url", "http://appdirect/event/12345");

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"success\":true}"));
    }

    @Test
    public void shouldReturnBadRequestWhenUrlParamIsMissing() throws Exception {
        MockMvc mvc = createMockMvcWithAlwaysValidURL();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CANCEL_URL)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenUrlParamIsNotAValidUrl() throws Exception {
        MockMvc mvc = createMockMvcWithNeverValidURL();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CANCEL_URL)
                .accept(MediaType.APPLICATION_JSON)
                .param("url", "notAValidUrl");

        assertPerformRequestBuilderThrowRootCause(mvc::perform, requestBuilder, new IllegalArgumentException("The 'url' parameter is not a valid url"));
    }

    private MockMvc createMockMvcWithAlwaysValidURL() {
        return createMockMvc(url -> false);
    }

    private MockMvc createMockMvcWithNeverValidURL() {
        return createMockMvc(url -> true);
    }

    private MockMvc createMockMvc(Validator<String> urlValidator) {
        CancelUserService cancelUserService = eventUrl -> aSuccessfulResponse();
        CancelNotificationController cancelNotificationController = new CancelNotificationController(urlValidator, cancelUserService);
        return MockMvcBuilders.standaloneSetup(cancelNotificationController).build();
    }
}
