# Backend-API-Store-Fetch-Questions

### Description of this SpringBoot Application Setup and API Endpoints

Clone this Project and Run the Springboot Application.
Application uses tomcat server running on port 8080.

Once the Application Starts we can access the Swagger UI and H2 Console, H2 is in memory Database here.

**Swagger UI Link** - http://localhost:8080/swagger-ui.html

**H2 Console Link** - http://localhost:8080/h2-console

H2 Console requires some information before we can access the database and tables.

Driver Class - org.h2.Driver
JDBC URL - jdbc:h2:mem:testdb
User name - sa
password - 

** Please leave the password field blank and click connect to access the tables**

API Endpoints Description-
1. **GET /fetchAndStoreInDb** - This Api Fetches 5 Questions through the API Endpoint **"https://jservice.io/api/random"** and 
Saves in H2 - In Memory Database.
Remember to Hit this API before hitting other two APIs

2. **GET /play** - This Api fetches a single question with id.
3. **POST /next** - This Api takes answer and question id as payload and in response gets correct answer and next question details.

