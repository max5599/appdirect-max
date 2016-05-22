package com.mct.appdirect.utils;

import com.mct.appdirect.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest({"server.port=0"})
@SqlGroup({
        @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:db/user/cleanTables.sql")})
public abstract class IntegrationTest {

    @Value("${local.server.port}")
    protected int port;

    protected final RestTemplate template = new TestRestTemplate();

    protected String getBaseUrl() {
        return "http://localhost:" + port;
    }
}