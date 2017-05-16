package nl.ing.api;

import java.util.Random;

public class Call {

    private final String sid = randomSid("CA");

    public String toJson() {
        return "{\n" +
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
                "  \"uri\": \"/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls/" + sid + ".json\",\n" +
                "  \"subresource_uris\": {\n" +
                "    \"notifications\": \"/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls/" + sid + "/Notifications.json\",\n" +
                "    \"recordings\": \"/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls/" + sid + "/Recordings.json\"\n" +
                "  }\n" +
                "}";
    }

    public String getSid() {
        return sid;
    }

    public String getPath() {
        return "/2010-04-01/Accounts/AC11111111111111111111111111111111/Calls/" + sid + ".json";
    }

    private String randomSid(String prefix) {
        Random random = new Random();
        return String.format("%s%08x%08x%08x%08x", prefix, random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());
    }
}
