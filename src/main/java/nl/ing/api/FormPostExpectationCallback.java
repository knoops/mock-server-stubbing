package nl.ing.api;

import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FormPostExpectationCallback implements ExpectationCallback {

    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        Map<String, List<String>> body = new HashMap<>();
        String bodyString = httpRequest.getBodyAsString();
        if (bodyString != null) {
            for (String param : bodyString.split("&")){
                String[] keyval = param.split("=");
                String key = URLDecoder.decode(keyval[0]);
                String val = keyval.length > 1 ? URLDecoder.decode(keyval[1]) : "";
                List<String> values = body.get(key);
                if (values == null) {
                    values = new ArrayList<>();
                    body.put(key, values);
                }
                values.add(val);
            }
        }
        return handle(httpRequest, body);
    }

    public abstract HttpResponse handle(HttpRequest httpRequest, Map<String, List<String>> body);
}
