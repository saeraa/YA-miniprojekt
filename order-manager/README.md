This project is used as a basis for DevOps projects.

## Work log 

2019-04-15 Initial project created with a table showing the available orders.
The table is populated from the Business Data API by REST call to the endpoint **servername:port/orders**
  
2019-04-17 Settings file for server url and port was added in file src/properties/settings.json. This file is to be used by the DevOps team to guide the client to the desired application server.  
  
2019-04-20 Added functionality for filtering orders by customer id. Date format for shipping data was also corrected.

2019-04-25 Added delete functionality that delete orders from the database by calling the API.   
WARNING! Delete is immediate and does not ask the user before executing the request following click on delete link. 
