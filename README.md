# WorkflowManager

A small CQRS-based proof of concept that demonstrates using a command service, a replication, event consumer, service, RabbitMQ messaging, and a query service backed by MongoDB. The write model persists in PostgreSQL; the read model is served from MongoDB. Docker is used to run the messaging and databases during development.

## **System Design** for better understading

<img src="imgs/Architecture - System Design.jpg" style="width:50%">

## Technologies

- SpringBoot 3.5.6
- Swagger
- RabbitMQ
- Mongo
- Postgres
- Docker

## Core idea / How it works

Client sends a write request to the Command Service.

The Command Service performs the change in PostgreSQL.

After persisting, the Command Service publishes a domain event to RabbitMQ.

The Replication Service listens to the exchanges/queues, consumes events, and updates the MongoDB read model to reflect the latest state.

The Query Service reads only from MongoDB and returns data to the client.

This gives an eventual-consistency model: writes are immediately persisted in PostgreSQL, and reads reflect those changes after the replication pipeline processes the events.
