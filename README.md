# iCommerce
# Technical stack
The architecture supports the following technologies:
* Frameworks - Spring boot, Spring Cloud, Feign, Eureka, Resilience4.
* API Management: Spring Rest Docs, Swagger
* Security: JWT (JSON Web Token) with OIDC, OpenID Connect with MITREid Connect, OAuth 2.0,  “UAA” (User Account and Authentication) server.
* Databases - PostgreSQL, MongoDB, Redis
* Serverless functions like AWS Lambda or Firebase Functions for notifications
* Server Side - Spring Boot, Spring Security, Maven, Hibernate, Liquibase, Cucumber, ArchUnit, Gatling, Elasticsearch, Kafka, Elastic Stack, Prometheus
* Cloud - AWS
* Message brokers - Apache Kafka, RabbitMQ, and Redis Streams
* Firebase
* CI / CD - Jenkins, Github Workflows, Docker, Docker Compose, Kubernetes, Minikube, Minishift, ECS (Elastic Container Service), EKS (Elastic Kubernetes Service)
# Prerequisites
This is a gradle project. However, you need to have installed:
- Java Development Kit >= 11
- Docker
- Docker compose
- Gradle
- Kubernates CLI
- Minikube
# Module & Service
- [x] api-gateway
- [x] order-service
- [x] audit-service
- [x] product-service
- [x] registry-service
- [x] shopping-cart-service
- [x] config-service
- [x] auth-server
# Using pattern
- Service discovery & Service registry: Eureka
- Load balancing: Spring cloud Loadbalancer
- Reliability Circuit-breaking and Fault-tolerance: Resilience4j, Istio, Service Mesh.
- API gateway: Spring cloud gateway
- Externalized configuration: Spring cloud config
- Failure- and latency-aware, load balancing, cluster failover and retry
- Distributed tracing: Spring cloud sleuth.
- Hexagonal Architecture (Ports and Adapters pattern)
- Consumer Driven Contract: Spring cloud contract.
- Security: Access token wiht Okta, Spring cloud security.
- Observability:
    - Log aggregation: AWS cloudwatch
    - Application metrics - instrument a service's code to gather statistics about operations
    - Audit logging - record user activity in a database
    - Distributed tracing - zipkin, sleuth, Raygun and Airbrake
    - Health check API: Eurekaclient, spring boot admin and spring boot actuator
    - Log deployments and changes: Github, AWS cloudtrail and LogDNA
- Deployment pattern
    - Service instance per container - Dockerfile and Dockercompose
    - Serverless deployment - deploy as service using serverless deployment platform
    - Service deployment platform - deploy services using a highy automated platform that provides a service abstraction
- Transaction 
  - Saga orchestration
- Data management
  - Database per service - each service has its own private database
  - Domain event - publish an event whenever data changes
  - Event sourcing - persist aggregates as a sequence of events
# High level design

![](/images/envent-service.drawio.png)
# Database Design