# Java Inter-Process & Multithreaded Messaging

A pure Java application demonstrating core system-level concepts: Multithreading, Inter-Process Communication (IPC) via TCP Sockets, and Interface-Driven Architecture. 

This project simulates a generic "Ping-Pong" messaging system between two entities (Players). It is designed to showcase how strict Separation of Concerns allows the exact same business logic to run in two fundamentally different architectural environments without modification.

## 🚀 Features

* **Pure Java:** Built without external frameworks (like Spring) to demonstrate a fundamental understanding of the JVM and standard libraries.
* **Single-Process Concurrency:** Utilizes Java `Thread`s and thread-safe `BlockingQueue`s for safe, lock-free memory sharing.
* **Multi-Process Networking:** Utilizes TCP `Socket`s and `ServerSocket`s for communication between completely isolated processes.
* **Clean Architecture:** The core `Player` logic is entirely decoupled from the transport layer using a `MessageChannel` interface.

## 🧠 Architecture & Design

The system relies heavily on the **Single Responsibility Principle** and **Dependency Injection**. 

* **`MessageChannel` (Interface):** Defines the strict contract (`sendMessage`, `receiveMessage`). It hides the underlying technology from the application logic.
* **`Player`:** Contains the business rules (message mutation, tracking counters, stop condition at 10 messages). It does not know if it is communicating over a local queue or a network port.
* **`QueueMessageChannel`:** The transport implementation for multithreaded, same-process execution.
* **`SocketMessageChannel`:** The transport implementation for distributed, multi-process execution.

## 🛠️ Prerequisites

* **Java Development Kit (JDK):** Version 17 or higher
* **Maven:** For building the project
* **Bash Environment:** For executing the provided run script

## 💻 How to Run

The easiest way to run the application is using the provided `start.sh` script, which automatically cleans, compiles, and executes the code. 


Mode A: Single Process (Threads)
Runs both the Initiator and the Receiver in the same JVM memory space using multithreading.

```bash
./start.sh
```


Fallback: Running Manually via Maven
If you do not have a bash environment available, you can run the commands manually:

Compile the project:
```bash
mvn clean compile
```


Run Single Process:
```bash
mvn exec:java -Dexec.mainClass="org.messaging.Main"
```


Run Multi-Process (Requires two terminal windows):

# Terminal 1
```bash
mvn exec:java -Dexec.mainClass="org.messaging.ServerApp"
```

# Terminal 2
```bash
mvn exec:java -Dexec.mainClass="org.messaging.ClientApp"
```
