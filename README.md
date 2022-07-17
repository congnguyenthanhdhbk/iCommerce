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
    - Service deployment platform - deploy services using a highly automated platform that provides a service abstraction
- Transaction 
  - Saga orchestration
- Data management
  - Database per service - each service has its own private database
  - Domain event - publish an event whenever data changes
  - Event sourcing - persist aggregates as a sequence of events
# High level design

![](/images/envent-service.drawio.png)
# Database Design
## Product service
```
{
	"id" : "string",
	"name" : "string",
	"description" : "string",
	"colors" : ["string"],
	"brand" : "string",
	"categories" : ["string"],
	"quantity" : "number",
	"price" : "number"
}
```
## shopping cart service
```
{
	"id" : "string",
	"productIds" : [ "string" ],
	"totals" : "number",
	"userId" : "string",
	"quantity" : "number",
}
```
# API design
## Product service
### Add product
```
curl --location --request POST 'http://localhost:8082/v1/products' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Áo thun Teeworld Why Fall In Love When You Can Fall Asleep Nam Nữ Unisex",
    "description": "Chất liệu 100% cotton, co dãn và thấm hút mồ hôi tốt",
    "colors": [
        "Black",
        "White"
    ],
    "brand": "Teeworld",
    "categories": [
        "Áo thun Teeworld Why Fall In Love When You Can Fall Asleep Nam Nữ Unisex"
    ],
    "price": 299000,
    "quantity": 300
}'
```
### Update product
```
curl --location --request PUT 'http://localhost:8082/v1/products/62c2f597876dc4651fd6aab7' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Clothers",
    "description": "Chất liệu 100% cotton, co dãn và thấm hút mồ hôi tốt",
    "colors": [
        "Black",
        "White"
    ],
    "brand": "Teeworld",
    "categories": [
        "Áo thun Teeworld Why Fall In Love When You Can Fall Asleep Nam Nữ Unisex"
    ],
    "price": 299000,
    "quantity": 300
}'
```
### Get list product
```
curl --location --request GET 'http://localhost:8082/v1/products?page=0&size=20&filterAnd=name%7Clike%7CÁo&filterOr=&orders=name%7CDESC'
```
### Get product by id
```
curl --location --request GET 'http://localhost:8082/v1/products/62c2f597876dc4651fd6aab7' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Áo thun Teeworld Why Fall In Love When You Can Fall Asleep Nam Nữ Unisex",
    "description": "Chất liệu 100% cotton, co dãn và thấm hút mồ hôi tốt",
    "colors": [
        "Black",
        "White"
    ],
    "brand": "Teeworld",
    "categories": [
        "Áo thun Teeworld Why Fall In Love When You Can Fall Asleep Nam Nữ Unisex"
    ],
    "price": 299000,
    "quantity": 300
}'
```
## Shopping cart service
### Add to cart
```
curl --location --request POST 'http://localhost:8989/api/v1/carts' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productIds": [
        "62d3dc46506d803efef5a045",
        "62d3f12a506d803efef5a046"
    ],
    "userId": "1"
}'
```
### Update cart
```
curl --location --request PUT 'http://localhost:8989/api/v1/carts/62d3f168b07d842067048193' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productIds": [
        "62d3dc46506d803efef5a045",
        "62d3f12a506d803efef5a046"
    ]
}'
```
# Coding structure
In the project, I follow Hexagonal Architecture
1. Controller to define how to external system communication with service
2. Service to support for handle user case or business of the service or domain
3. Repository define to interact with database
4. Entity to define domain
5. Handlers to define how to this service communication with external tools or opensource to send info from internal to external
6. DTO and ETO to define the method to connect between service and domain or transferring message for connection with external system
# TODO
## Saga orchestration
![](/images/saga.png)
All of state only continue to go ahead when each local transaction for each service which is success.
## API gateway
1. Api gateway for app and web to support for front-end side
2. Api gateway for operator to support for backend side
## Backend internal service
1. Inventory service: To manage available products which is already for order of customers
2. Promotion service: To provide discount method as apply coupon, direct discount, loyalty etc
3. Payment service: To handle for payment as connecting to bank, visa / master
4. Order service: To handle for placing order of customers
5. Search service: To support for customer who can use tool to query or recommendation matching product by applying elastic search
## Recommendation for build AWS
### Service and tools to use for build infra
1. API gateway -> to user rest api
2. AppMesh -> A service mesh to balance traffic or monitoring
3. EKS -> To build K8S to host service as pods
4. Cloudwatch to monitor logs
5. ECR -> to storage docker image as registry


