package nl.ing.api;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class CreateCallMock extends FormPostExpectationCallback {

    private Random random = new Random();
    private MockServerClient mockServerClient;

    public CreateCallMock(MockServerClient mockServerClient) {
        super();
        this.mockServerClient = mockServerClient;
    }

    @Override
    public HttpResponse handle(HttpRequest httpRequest, Map<String, List<String>> body) {

        Call call = new Call();
        mockServerClient.when(
                HttpRequest.request()
                        .withPath(call.getPath())
                        .withMethod("GET")
        ).callback(
                new GetCallMock(mockServerClient, call)
        );
        return response().withBody(json(call.toJson()));
    }

    private String randomSid(String prefix) {
        return String.format("%s%08x%08x%08x%08x", prefix, random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());
    }
}
