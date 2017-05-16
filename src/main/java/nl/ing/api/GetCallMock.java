package nl.ing.api;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class GetCallMock implements ExpectationCallback {

    private final Call call;
    private final MockServerClient mockServerClient;

    public GetCallMock(MockServerClient mockServerClient, Call call) {
        super();
        this.call = call;
        this.mockServerClient = mockServerClient;
    }

    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        return response().withBody(json(call.toJson()));
    }
}
