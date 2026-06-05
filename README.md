# Apt Backend Assignment - Real-Time Order Updates using Spring Boot WebSocket

## Overview

This project demonstrates a real-time order update system built using Spring Boot, PostgreSQL, and WebSocket.

The application tracks changes to orders and instantly notifies all connected clients whenever an order is created, updated, or deleted.

The goal is to eliminate polling and provide efficient real-time communication between the server and clients.

---

## Features

* Create Order
* Get All Orders
* Get Order By ID
* Update Order Status
* Delete Order
* Real-Time Updates using WebSocket
* PostgreSQL Persistence
* DTO-Based API Design
* Browser Client for Live Updates

---

## Tech Stack

### Backend

* Java 17
* Spring Boot 4
* Spring Data JPA
* Spring WebSocket
* PostgreSQL
* Lombok

### Client

* HTML
* JavaScript
* SockJS
* STOMP

---

## Architecture

```text
Browser Client
      │
      │ WebSocket (/ws)
      ▼
Spring Boot Application
      │
      │ SimpMessagingTemplate
      ▼
WebSocket Broker (/topic/orders)
      │
      ▼
PostgreSQL Database
```

---

## Design Approach

### Why WebSocket?

Traditional REST APIs require polling to check for updates.

Example:

```text
Client -> GET /orders
Client -> GET /orders
Client -> GET /orders
```

This increases network traffic and introduces latency.

Using WebSocket, clients establish a persistent connection with the server.

Whenever an order changes:

```text
Database Change
      ↓
Service Layer
      ↓
WebSocket Event
      ↓
Connected Clients
```

The update is pushed instantly without requiring additional requests.

---

## REST Endpoints

### Create Order

```http
POST /orders
```

### Get All Orders

```http
GET /orders
```

### Get Order By Id

```http
GET /orders/{id}
```

### Update Order

```http
PUT /orders/{id}
```

### Delete Order

```http
DELETE /orders/{id}
```

---

## WebSocket Flow

Clients connect to:

```text
/ws
```

Clients subscribe to:

```text
/topic/orders
```

Whenever an order is:

* Created
* Updated
* Deleted

The backend publishes a message using:

```java
messagingTemplate.convertAndSend("/topic/orders", payload);
```

All subscribed clients receive the update instantly.

---

## Project Structure

```text
src
├── controller
├── service
├── repository
├── model
├── dto
├── config
└── resources
    └── static
        └── index.html
```

---

## Running the Project

### Clone Repository

```bash
git clone <repository-url>
```

### Configure PostgreSQL

Create database:

```sql
CREATE DATABASE websocket;
```

Update credentials inside:

```properties
application.properties
```

### Run Application

```bash
mvn spring-boot:run
```

or run directly from your IDE.

### Open Client

```text
http://localhost:8080/index.html
```

---

## Screenshots

### WebSocket Client Connected

![WebSocket Connected](screenshots/Screenshot%202026-06-05%20190221.png)

### Create Order Event

![Create Order](screenshots/Screenshot%202026-06-05%20190247.png)

### Update Order Event

![Update Order](screenshots/Screenshot%202026-06-05%20190316.png)

### Delete Order Event

![Delete Order](screenshots/Screenshot%202026-06-05%20190337.png)

### Real-Time Notification

![Real Time Notification](screenshots/Screenshot%202026-06-05%20190401.png)

---

## Scalability Considerations

The current implementation uses Spring's Simple Broker which is suitable for development and small-scale deployments.

For production-scale systems, the architecture can be extended using:

* RabbitMQ
* Apache Kafka
* Redis Pub/Sub

This enables:

* Horizontal Scaling
* High Throughput
* Distributed Event Processing
* Fault Tolerance

---

## Evaluation Criteria Coverage

### Design Thinking

* Event-driven architecture
* Real-time communication
* Extensible for Kafka/RabbitMQ integration
* Reduced polling overhead

### Correctness

* Database changes trigger instant client updates
* Create, Update, and Delete operations verified

### Code Quality

* Layered architecture
* DTO pattern
* Separation of concerns
* Clean package structure

### Documentation

* Setup instructions
* Architecture explanation
* Screenshots
* Design decisions
* Scalability considerations

---

## Conclusion

This solution demonstrates a real-time backend system where database changes are propagated instantly to connected clients using Spring Boot WebSocket. The architecture is modular, maintainable, and designed with future scalability in mind.
