# E-Commerce For Checkout
Checkout Task Assignment for Watch Catalogue

# APIs For Watch catalogue APIs
Spring Boot Project For Different Kind Of Watch catalogue APIs.

# Project build requirements
Java 1.8
Maven: Version 3
Spring Boot v2.1.2


# Building And Running the Application Locally
                        ---------------- Local Development ------------------
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Spring Boot v2.1.2] (https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/)

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.kunal.ecommerce.ECommerceCheckoutApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```           

# Application Feature's
The application provides RESTful API micro-services to add , fetch and delete items to a Watch Catalogue API’s and calculate the checkout total of watch items.

The application provides the features of checkout where it calculates the list of watch items put in the cart and outputs the total cost.

# Accessing the application
The application is accessible at http://localhost:8080/checkout/ and at also other endpoints as defined in the Controller classes.

For instance - 1. http://localhost:8080/checkout/itemsList?items=
               2. http://localhost:8080/product/addProducts
               3. http://localhost:8080/product/listProducts
               4. http://localhost:8080/offer/addOffers
               5. http://localhost:8080/offer/listOffers
               6. http://localhost:8080/checkout/itemsList?items=Rolex,Michael Kors,Swatch,Casio
               

# Unit Testing with Spring, JUnit and Mockito
The application built has been tested with the Junit 5 and Mockito Framework.

Please refer to the test packages under the source code.

# Postman Collection
The Postman Collection is provided under the project root directory with a folder name postmanCollection which can be imported for reference.
