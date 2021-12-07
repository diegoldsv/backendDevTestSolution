# backendDevTestSolution

This application expose an endpoint to find similar products from a product Id.   
*Solution to the test: [backendDevTest](https://github.com/dalogax/backendDevTest)*  


---
## Technology

- Spring Boot 2.5.6
- JDK 17

---
## How to execute the app

**Requirements**

- backendDevTest downloaded
- backendDevTestSolution (this repo) downloaded

**Instructions**

First, you have to start the mocks defined in the repository **backendDevTest**.
Follow the instructions explained there to start the mocks from Docker and then come back here.

To run the app backendDevTestSolution you have two options:

- Using mvnw script (included in the project)
- Running the .jar file (you can find it in the ./jar folder)

### mvnw 

**Requirements**

* JDK installed
* Environment variable: JAVA_HOME pointing to the JDK location in your machine

**Start the app**

Open a terminal in the project folder and execute:
```cmd
.\mvnw spring-boot:run
```

### JAR

**Requirements**

- Java Runtime Environment installed

**Start the app**

Open a terminal in the project folder and execute:
```cmd
java -jar .\jar\technicaltest-0.0.1-SNAPSHOT.jar
```

---
## How to test the app

Once the app is launched, it will be running on port 5000.
You can test the endpoint: GET /product/{productId}/similar

1. Open a web browser
2. Type http://localhost:5000/product/1/similar

*Note: In the repository [backendDevTest](https://github.com/dalogax/backendDevTest) you can find how to launch a test script which uses the k6 testing tool* 
