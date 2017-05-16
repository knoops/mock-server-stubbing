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

    private final String keepAliveJson = "{\"httpRequest\" : {\"method\" : \"GET\",\"path\" : \"/keepalive2\"},\"times\" : { \"remainingTimes\" : 0, \"unlimited\" : true}, \"timeToLive\" : { \"unlimited\" : true}, \"httpResponse\" : { \"statusCode\" : 200, \"body\" : \"OK\"}}";

    @Test
    public void testDynamicKeepAlive() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:9000/expectation", keepAliveJson);

        String response = restTemplate.getForObject("http://localhost:9000/keepalive2", String.class);
        System.err.println(response);
    }
}