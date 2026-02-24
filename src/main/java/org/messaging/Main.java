package org.messaging;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Responsibility: The entry point of the application.
 * Initializes the queues, creates the players, and starts their threads.
 */
public class Main {

    public static void main(String[] args) {
        // Create two thread-safe queues. Capacity of 1 is enough for strict turn-taking.
        BlockingQueue<String> queue1to2 = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> queue2to1 = new ArrayBlockingQueue<>(1);

        // Cross the queues: Initiator writes to 1to2 and reads from 2to1
        MessageChannel initiatorChannel = new QueueMessageChannel(queue1to2, queue2to1);
        // Receiver writes to 2to1 and reads from 1to2
        MessageChannel receiverChannel = new QueueMessageChannel(queue2to1, queue1to2);

        // Requirement 1: Create 2 players
        Player initiator = new Player("Initiator", true, initiatorChannel);
        Player receiver = new Player("Receiver", false, receiverChannel);

        // Wrap players in standard Java Threads
        Thread initiatorThread = new Thread(initiator);
        Thread receiverThread = new Thread(receiver);

        System.out.println("Starting application in a single process...");

        // Start the threads. The OS will run them simultaneously.
        receiverThread.start();
        initiatorThread.start();
    }
}