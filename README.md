# order-management-system

Spring Boot Application written on Java 11 that serves REST API for creating orders for some products.
Application is using Spring Security to differentiate between user-specific roles : Manager and Client (Admin & User).

# Accessing the API with specific role. You can test different endpoints using 2 predefined roles:
- Admin. 
- 1) username = manager
- 2) password = managerpassword
- User.
- 1) username = user
- 2) password = password

Manager have these predefined functions:
-
1) Able to add products to the database, so that clients will be able to buy them;
2) Able to list all the available products, their prices and quantities;

Client have these predefined functions:
-
1) Able to list all the available product, their prices and quantities;
2) Able to place orders for products(multiple items per order are allowed);
3) Able to pay for own order(mark them as paid; - no actual payment functionality was implemented).
4) Application manages the risks and automatically delete not paid orders after 10 minutes after creation.

# Application is using H2 DB for storing data.
Some initial data are added from the start using CommandLineRunner. You are free to use the application using Postman, Curl  
or any other API Client. Few simple examples are provided in the resources package (sampleDataForPostman.txt). 