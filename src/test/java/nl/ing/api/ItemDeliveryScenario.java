package nl.ing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

public class ItemDeliveryScenario {

    private static AtomicInteger items = new AtomicInteger();
    private static AtomicInteger deliveries = new AtomicInteger();
    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static MockServerClient mockServerClient = startClientAndServer(1080);

    static String failingItemDelivery() {
        String itemName = "item" + items.incrementAndGet();
        mockServerClient
                .when(
                        request("/deliveries")
                                .withMethod("POST")
                                .withBody(json("{item: '" + itemName + "'}")),
                        Times.once()
                )
                .callback(
                        new FailingDeliveryRequestCallback()
                );
        return itemName;
    }

    static class FailingDeliveryRequestCallback implements ExpectationCallback {

        final String deliveryId = "delivery" + deliveries.incrementAndGet();

        @Override
        public HttpResponse handle(HttpRequest httpRequest) {
            try {
                DeliveryRequest deliveryRequest = fromRequest(httpRequest);
                Delivery delivery = new Delivery();
                delivery.item = deliveryRequest.item;
                delivery.deliveryId = deliveryId;
                delivery.deliveryStatus = "pending";

                executorService.schedule(() ->
                                callBackForStatus(deliveryRequest.deliveryStatusCallback, delivery, "failed"),
                        10, TimeUnit.SECONDS);

                return toResponse(delivery);
            } catch (Exception e) {
                return HttpResponse.response().withStatusCode(500);
            }
        }
    }

    static DeliveryRequest fromRequest(HttpRequest httpRequest) throws IOException {
        return objectMapper.reader(DeliveryRequest.class).readValue(httpRequest.getBodyAsString());
    }

    static HttpResponse toResponse(Delivery delivery) throws IOException {
        return HttpResponse.response()
                .withBody(objectMapper.writeValueAsString(delivery))
                .withHeader("Content-Type", "application/json");
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
