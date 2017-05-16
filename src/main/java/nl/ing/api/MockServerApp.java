package nl.ing.api;

import org.mockserver.client.server.MockServerClient;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
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
                        .withPath("/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls.json")
                        .withMethod("POST")
        ).respond(
                response()
                        .withBody(json("{\n" +
                                "  \"sid\": \"CA11111111111111111111111111111111\",\n" +
                                "  \"date_created\": null,\n" +
                                "  \"date_updated\": null,\n" +
                                "  \"parent_call_sid\": null,\n" +
                                "  \"account_sid\": \"AC11111111111111111111111111111111\",\n" +
                                "  \"to\": \"+31611111111\",\n" +
                                "  \"to_formatted\": \"+31611111111\",\n" +
                                "  \"from\": \"+31200000000\",\n" +
                                "  \"from_formatted\": \"+31200000000\",\n" +
                                "  \"phone_number_sid\": \"PN11111111111111111111111111111111\",\n" +
                                "  \"status\": \"queued\",\n" +
                                "  \"start_time\": null,\n" +
                                "  \"end_time\": null,\n" +
                                "  \"duration\": null,\n" +
                                "  \"price\": null,\n" +
                                "  \"price_unit\": \"USD\",\n" +
                                "  \"direction\": \"outbound-api\",\n" +
                                "  \"answered_by\": null,\n" +
                                "  \"api_version\": \"2010-04-01\",\n" +
                                "  \"annotation\": null,\n" +
                                "  \"forwarded_from\": null,\n" +
                                "  \"group_sid\": null,\n" +
                                "  \"caller_name\": null,\n" +
                                "  \"uri\": \"/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls/CA11111111111111111111111111111111.json\",\n" +
                                "  \"subresource_uris\": {\n" +
                                "    \"notifications\": \"/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls/CA11111111111111111111111111111111/Notifications.json\",\n" +
                                "    \"recordings\": \"/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls/CA11111111111111111111111111111111/Recordings.json\"\n" +
                                "  }\n" +
                                "}")
                        )
        );


        mockServerClient
                .when(request().withPath("/keepalive"))
                .respond(response().withStatusCode(200).withBody("OK"));
    }
}
