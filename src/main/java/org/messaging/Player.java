package org.messaging;

/**
 * Responsibility: Represents a participant in the communication.
 * Handles the business logic: sending, receiving, concatenating counters,
 * and enforcing the 10-message stop condition.
 */
public class Player implements Runnable {

    private final String name;
    private final boolean isInitiator;
    private final MessageChannel channel;

    private int sentCounter = 0;
    private int receivedCounter = 0;
    private static final int MAX_MESSAGES = 10;

    public Player(String name, boolean isInitiator, MessageChannel channel) {
        this.name = name;
        this.isInitiator = isInitiator;
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            // Requirement 2: Initiator starts the communication
            if (isInitiator) {
                sentCounter++;
                String initialMessage = "Hello";
                System.out.println(name + " sending: " + initialMessage);
                channel.sendMessage(initialMessage);
            }

            // Requirement 4: Stop condition is exactly 10 received messages
            while (receivedCounter < MAX_MESSAGES) {

                String receivedMessage = channel.receiveMessage();
                receivedCounter++;
                System.out.println(name + " received: " + receivedMessage);

                // Requirement 3: Send back concatenated message, up to 10 sent messages
                if (sentCounter < MAX_MESSAGES) {
                    sentCounter++;
                    // Concatenate the received string with THIS player's sent counter
                    String replyMessage = receivedMessage + " " + sentCounter;

                    System.out.println(name + " sending: " + replyMessage);
                    channel.sendMessage(replyMessage);
                }
            }

            System.out.println(name + " finished gracefully. Sent: " + sentCounter + ", Received: " + receivedCounter);

        } catch (Exception e) {
            System.err.println(name + " encountered an error: " + e.getMessage());
        }
    }
}