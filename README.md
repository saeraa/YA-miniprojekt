# Microservices in Spring

![ya-miniproject drawio (1)](https://user-images.githubusercontent.com/99484730/212542334-f7e34c56-8f51-4264-a08f-5eb7cfbf6177.png)

## Project description
> • A company is planning to move its core business system to a microservice architecture 
and at the same time add new functionality.  
• Existing functionality need to be available for current frontend application to work.  
• Additional functionality include a foreign currency service that need to be integrated with 
core API to provide prices in other currencies.   
• Additional functionality also include a recommendation service where customers can add 
their recommendations for products.  
• Additional functionality also include a customer support service where customers can 
manage their support requests.  

### docker-compose 
This project is built to run in a docker compose environment. All parts of the program are registered in the /docker-compose.yml file and can be run by `docker-compose up`.

### business-database
The MySQL database server containing the databases Northwind, Recommendation and SupportIssues.

### keycloak
The frontend client and common-api are secured with Keycloak. No requests will be made without proper authorization. Example login is guest/guest. Once the project is up and running, the Keycloak admin panel can be reached at http://localhost:8080/. Realm, client and user information saved in its own PostgreSQL database.

### eureka-service
The service registration Netflix Eureka Server where all the parts are registered. Allows the different microservices to communicate with each other by name rather than address. Server details at http://localhost:8761/.

### recommendation
Spring Boot Microservice for adding product recommendations. Recommendations are displayed on the frontend, and there is a form to submit new ones.

### customer-support
Spring Boot Microservice for adding support issues. Support issues are displayed on the frontend, and there is a form to submit new ones.

### foreign-currency
Spring Boot Microservice for calculating foreign currency exchange. Retrieves current currency exchange rates from European Central Bank via their RESTful API. Use Currency tab on the frontend.

### common-backend
Spring Boot Microservice of the backend API holding it all together. Talks to the database and other microservices. Ships data to the frontend. API documentation for all endpoints available from Spring Docs/Asciidocs.

### frontend
Frontend client served at http://localhost:81/. Built with Vite, React and TypeScript. Uses the Keycloak server to login and sends auth token for every call to the common-backend API.
