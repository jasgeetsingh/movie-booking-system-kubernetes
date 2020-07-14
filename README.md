# Movie-Booking-System!

In this project I'm demonstrating Movie Booking System microservice-based architecture that is deployed on Kubernetes.

# Getting Started

Spring Cloud Kubernetes requires access to Kubernetes API in order to be able to retrieve a list of address of pods running for a single service. Just execute the following command:
> kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default


## Deploying Steps

1.  Build Maven project with using command:  `mvn clean install`
2.  Build Docker images for each module (you can find in each module directory folder) using command, for example:  `docker build -t jassgeeteden/admin:1.0 .`
3.  Go to  `/kubernetes`  directory in repository
4.  Apply all templates to Minikube in following order:  
4.1 `kubectl apply -f mysql-configmap.yaml`
4.2  `kubectl apply -f mysql-secret.yaml`
4.3 `kubectl apply -f mysql-root-secret.yaml`
4.4 `kubectl apply -f mysql-deployment.yaml`
4.5 `kubectl apply -f admin-deployment.yaml`
4.6 `kubectl apply -f booking-deployment.yaml`
4.7 `kubectl apply -f gateway-deployment.yaml`
4.8 `kubectl apply -f kubernetes\ingress.yaml`
5.  Check status with  `kubectl get pods`

## Architecture

  movie-booking system consists of the following modules:

-   **gateway-service**  - a module that Spring Cloud Netflix Zuul for running Spring Boot application that acts as a proxy/gateway in our architecture.
-   **admin-service**  - a module persist the data of Movie Booking System and allows to perform CRUD operations. Consist all the business logic required in order to perform new booking.
-   **booking-service**  - a module responsible for New Booking Request. It communicates with admin-service.
-   **config-service**  - Not Implemented Yet but definitely a separate module containing embedded Spring Boot Admin Server used for monitoring Spring Boot microservices running on Kubernetes
