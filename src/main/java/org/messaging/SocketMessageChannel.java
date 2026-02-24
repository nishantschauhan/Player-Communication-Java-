package org.messaging;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketMessageChannel implements MessageChannel {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public SocketMessageChannel(Socket socket) throws Exception {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void sendMessage(String message) throws Exception {
        writer.println(message);
    }

    @Override
    public String receiveMessage() throws Exception {
        return reader.readLine();
    }
}