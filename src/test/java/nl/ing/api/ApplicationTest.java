package nl.ing.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationTest.class);

    @Test
    public void deliveryFails() throws InterruptedException {
        String expectedItem = ItemDeliveryScenario.failingItemDelivery();

        // TODO: create a delivery for the expected item and wait for the callbacl
    }
}
