# Service-Center
There are Main class and 4 other classes in this project.

* 1. DatabaseConnection
* 2. ClientManager
* 3. ServicesManager
* 4. RequestManager


## DatabaseConnection
Using this class, you can connect to the database and close the connection.


## ClientManager
##### Using this class, you can add in database new client's information. If necessary, you can then edit this client's information or delete this client's information.
##### And finally you can see the information of all your clients.
##### Additionally, you can change the Last Request Date information of clients. (First read RequestManager).
*        clientManager.addNewClient();
*        clientManager.editClient();
*        clientManager.deleteClient();
*        clientManager.viewAllClient();
*        clientManager.editLastRequestDate();


## ServicesManager
##### Using this class, you can add in database new service information. If necessary, you can then edit this service information or delete this service information
and you can see the information of all your services.
*        servicesManager.addNewService();
*        servicesManager.editService();
*        servicesManager.deleteService();
*        servicesManager.viewAllServices();


## RequestManager
##### Using this class, you can add in database new request. ! When you create a new request, this command adds to the client's last request date and how much money has been paid.
##### After you can delete any request. ! When you delete any request, this command deletes the client's last request date and how much money has been paid.
##### You can see how many times your clients have used your services.
##### And finally you can see the information of all your requests.
*        requestManager.viewRequests();
*        requestManager.createRequest();
*        requestManager.deleteRequest();
*        requestManager.viewQuantity();
