package org.messaging;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) {
        int port = 5000;

        // ServerSocket listens for incoming network connections
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Receiver is running. Waiting for Initiator on port " + port + "...");

            // .accept() blocks (pauses) the program until someone actually connects
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection established! Starting communication...");

            // We use our new Socket channel
            MessageChannel channel = new SocketMessageChannel(clientSocket);

            // Create the Receiver player (isInitiator = false)
            Player receiver = new Player("Receiver", false, channel);

            // Because this is its own program, we don't need a separate Thread object.
            // We just run it directly in the main thread.
            receiver.run();

        } catch (Exception e) {
            System.err.println("Receiver error: " + e.getMessage());
        }
    }
}