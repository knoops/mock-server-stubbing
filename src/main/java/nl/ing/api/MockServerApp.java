package nl.ing.api;

import org.mockserver.client.server.MockServerClient;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class MockServerApp {

    private static MockServerClient mockServerClient = startClientAndServer(1080);

    public static void main(String[] args) {

    }
}
