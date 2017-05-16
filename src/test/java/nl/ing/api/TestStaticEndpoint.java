package nl.ing.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class TestStaticEndpoint {

    private final String staticUrl = "http://localhost:9000/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls.json";

    @Before
    public void setUp() {
        MockServerApp.configure();
    }

    @Test
    public void testStaticEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(staticUrl, "put-request-body-here", String.class);
        System.err.println(response);
    }
}
