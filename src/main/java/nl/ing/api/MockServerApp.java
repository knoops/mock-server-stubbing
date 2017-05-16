package nl.ing.api;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpClassCallback;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpClassCallback.callback;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class MockServerApp {

    private static MockServerClient mockServerClient = startClientAndServer(9000);

    public static void main(String[] args) throws InterruptedException {
        configure();
        System.out.println("Started");
        Thread.sleep(0);
    }

    static void configure() {
        mockServerClient.when(
                request()
                        .withPath("/2010-04-01/Accounts/.*/Calls.json")
                        .withMethod("POST")
        ).callback(
                new CallCollectionMock()
        );


        mockServerClient
                .when(request().withPath("/keepalive"))
                .respond(response().withStatusCode(200).withBody("OK"));
    }
}
