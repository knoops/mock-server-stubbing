package nl.ing.api;

import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class CallCollectionMock extends FormPostExpectationCallback {

    private Random random = new Random();

    @Override
    public HttpResponse handle(HttpRequest httpRequest, Map<String, List<String>> body) {

        String sid = randomSid("CA");
        return response()
                .withBody(json("{\n" +
                        "  \"sid\": \"" + sid + "\",\n" +
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
                );
    }

    private String randomSid(String prefix) {
        return String.format("%s%08x%08x%08x%08x", prefix, random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());
    }
}
