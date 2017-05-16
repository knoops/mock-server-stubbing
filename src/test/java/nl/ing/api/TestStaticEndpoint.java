package nl.ing.api;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class TestStaticEndpoint {

    private final String staticUrl = "http://localhost:9000/twilio/callsstatic";

    @Test
    public void testStaticEndpoint() {
        Delivery delivery = new Delivery();
        delivery.deliveryId = "1";
        delivery.item = "item";
        callBackForStatus(staticUrl, delivery, "OK");

    }

    static void callBackForStatus(String url, Delivery delivery, String status) {
        Delivery callbackDelivery = new Delivery();
        callbackDelivery.item = delivery.item;
        callbackDelivery.deliveryId = delivery.deliveryId;
        callbackDelivery.deliveryStatus = status;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, callbackDelivery, String.class);
    }
}
