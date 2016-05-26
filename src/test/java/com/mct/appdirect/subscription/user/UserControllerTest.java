package com.mct.appdirect.subscription.user;

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

import java.util.Arrays;

import static com.mct.appdirect.subscription.user.UserBuilder.aUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class UserControllerTest {

    @Test
    public void shouldReturnOkWithTheUsers() throws Exception {
        MockMvc mvc = createMockMvc();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .accept(MediaType.TEXT_PLAIN);

        String expectedContent = "User 1 with email max@ence.com is Maxence Cramet and he or she is active\n" +
                "User 2 with email jus@tine.com is Justine Westelynck and he or she is inactive";

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
    }

    private MockMvc createMockMvc() {
        User maxence = aUser().withId(1).withEmail("max@ence.com").active().withFirstName("Maxence").withLastName("Cramet").build();
        User justine = aUser().withId(2).withEmail("jus@tine.com").inactive().withFirstName("Justine").withLastName("Westelynck").build();
        UserController controller = new UserController(()-> Arrays.asList(maxence, justine));
        return MockMvcBuilders.standaloneSetup(controller).build();
    }

}