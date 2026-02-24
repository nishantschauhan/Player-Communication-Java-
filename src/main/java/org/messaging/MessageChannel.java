package org.messaging;

public interface MessageChannel {
    void sendMessage(String message) throws Exception;
    String receiveMessage() throws Exception;
}
