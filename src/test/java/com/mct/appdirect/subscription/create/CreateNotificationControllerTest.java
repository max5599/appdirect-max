package com.mct.appdirect.subscription.create;

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

import static com.mct.appdirect.subscription.response.CreateResponseBuilder.aSuccessfulResponseWithAccountIdentifier;
import static com.mct.appdirect.utils.AssertNestedServetException.assertPerformRequestBuilderThrowRootCause;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CreateNotificationControllerTest {

    private static final String SUBSCRIPTION_CREATE_URL = "/subscription/create";

    @Test
    public void shouldReturnOkWithTheResponse() throws Exception {
        MockMvc mvc = createMockMvcWithAlwaysValidURL();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CREATE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .param("url", "http://appdirect/event/12345");

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"accountIdentifier\":\"123abc\", \"success\":true}"));
    }

    @Test
    public void shouldReturnBadRequestWhenUrlParamIsMissing() throws Exception {
        MockMvc mvc = createMockMvcWithAlwaysValidURL();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CREATE_URL)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenUrlParamIsNotAValidUrl() throws Exception {
        MockMvc mvc = createMockMvcWithNeverValidURL();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CREATE_URL)
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
        CreateUserService createUserService = eventUrl -> aSuccessfulResponseWithAccountIdentifier("123abc");
        CreateNotificationController createNotificationController = new CreateNotificationController(urlValidator, createUserService);
        return MockMvcBuilders.standaloneSetup(createNotificationController).build();
    }
}
