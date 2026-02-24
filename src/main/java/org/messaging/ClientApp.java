package org.messaging;

import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try {
            System.out.println("Initiator is starting. Connecting to Receiver...");
            // Connect to the Server
            Socket socket = new Socket(host, port);

            // Wrap the connection in our channel
            MessageChannel channel = new SocketMessageChannel(socket);

            // Create the Initiator player (isInitiator = true)
            Player initiator = new Player("Initiator", true, channel);

            // Run the logic
            initiator.run();

        } catch (Exception e) {
            System.err.println("Initiator error: " + e.getMessage());
            System.err.println("Make sure ServerApp is running first!");
        }
    }
}