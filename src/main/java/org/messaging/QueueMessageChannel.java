package org.messaging;

import java.util.concurrent.BlockingQueue;

/**
 * Responsibility: Implements in-memory message passing between threads
 * using BlockingQueues to ensure thread safety without manual locking.
 */
public class QueueMessageChannel implements MessageChannel {

    private final BlockingQueue<String> outQueue;
    private final BlockingQueue<String> inQueue;

    public QueueMessageChannel(BlockingQueue<String> outQueue, BlockingQueue<String> inQueue) {
        this.outQueue = outQueue;
        this.inQueue = inQueue;
    }

    @Override
    public void sendMessage(String message) throws InterruptedException {
        // Adds message to the queue, waiting if necessary for space
        outQueue.put(message);
    }

    @Override
    public String receiveMessage() throws InterruptedException {
        // Retrieves message, blocking the thread until a message is available
        return inQueue.take();
    }
}