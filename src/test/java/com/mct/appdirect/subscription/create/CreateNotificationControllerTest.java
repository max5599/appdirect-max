package com.mct.appdirect.subscription.create;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CreateNotificationControllerTest {

    private static final String SUBSCRIPTION_CREATE_URL = "/subscription/create";

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new CreateNotificationController()).build();

    @Test
    public void shouldReturnOk() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CREATE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .param("url", "http://appdirect/event/12345");

        mvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestWhenUrlParamIsMissing() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SUBSCRIPTION_CREATE_URL)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder).andExpect(status().isBadRequest());
    }
}
