# team_e_p1_webapp

Java Developers: James Fallon and Kyle Plumber
DevOps Engineers: Jacob Bulloch and David Pearl

**Note:** This webapp depends on a custom-built ORM, which can be found here:  
https://github.com/jpbulloch5/automating-agile-orm

## DevOps
  
The DevOps repo was cloned to GitHub from Azure Repos for the sole purpose of having a copy in my personal repository.
The CI/CD pipelines are designed for use with Azure DevOps and require configuration to match the requirements of the virtual machine(s) to which you would like to deploy the webapp, for example, the ssh key, known hosts, and ansible inventory files will all need to be changed to match your deployment environment.  
Alternatively, if you would like to test the functionality without setting up a virtual machine, you could download only the docker-compose file from this repo and run the application locally through Docker, in which case you can access the endpoints via localhost:9052/webapp/\<endpoint> where the \<endpoint> is the desired endpoint as described by the Java Developers below.
Note: This docker-compose setup uses a test image of the postgreSQL database that is pre-loaded with some dummy-values for testing purposes.  If you would like to test the functionality with other pre-loaded data, you can do so by creating your own postgreSQL file called initialization.sql in the same directory as a Dockerfile with the following contents:
```Dockerfile
FROM postgres:latest

# add table initialization
ADD ./initialization.sql /docker-entrypoint-initdb.d
RUN chmod a+r /docker-entrypoint-initdb.d/*

EXPOSE 9050
 ```
 To create your custom database docker image, run `docker build . -t my_db` on the command line from the directory that contains your Dockerfile and initialization.sql files.
 Then, alter line 12 of the docker-compose.yml file so that the application will use your docker image instead of the `jpbulloch5/airline_db:test` image.
 
 To test the functionality of the pipeline itself, however, you would need to connect this repo to Azure DevOps and configure the connections to your desired deployment environments.

## Ticket purchasing microservice

This webapp exposes a small number of endpoints to serve an airline ticketing kiosk. It allows the UI running on the kiosk
to display flights, and purchase tickets. To facilitate a demonstration of this functionality there are also endpoints for
looking up customers and creating flights.


## Building This Project Locally
 Getting into the details of IDEs and building java projects is outside the scope of this readme, however here is a quick 
and dirty overview to suppliment more in-depth tutorials:

 We suggest you begin with the IntelliJ IDE. If you want to test the exposed HTTP API endpoints, we suggest software called Postman. If you are looking for 
 a SQL workspace, try DBeaver.
 
 In order to build this project in a local environment you will also need to install Smart Tomcat, a simple free web server which integrates with IntelliJ. Once you have IntelliJ and Smart Tomcat installed you should be able to build this project in just a few minutes with a few simple configuration steps.
 
 In the top right quadrant of IntelliJ there are UI buttons for building projects. You should see a hammer icon, a drop-down which may be empty, as well as a play button, a debug button, a run with coverage button, and a stop button. We need a build configuration that launches our application with Tomcat. Click the drop-down box and select "Edit Configurations...".
 
 If you don't already see Smart Tomcat as an option in the run configuration templates (left panel) click the "+" button at the top of that panel and add Smart Tomcat. We need to set up a name for the configuration, the server location, the deployment directory, context path, and server port.
 
 - Name it something appropriate.
 - The Tomcat server location is the root of your tomcat server folder. Inside that folder you should see bin, conf, lib, logs and other folders.
 - This directory should be inside the project folder. This project includes a directory already for this purpose: /src/main/webapp/
 - The context path is added to your URL to access the endpoints. This is in case there are multiple different apps on the same server and port. This path is inserted before the endpoints, for instance: http://localhost:8080/CONTEXTPATH/ping
 - Port: The default is 8080. This is the port the app will be accessible on.

With these settings in place, hit Apply/OK and you are ready to go. Select your new Smart Tomcat configuration from the drop-down and click the play button to run your server.
You will see a bunch of red text rolling through the terminal, these are not necessarily errors. Look for the line indicating your server is running which will be something like: "16-Jun-2021 13:08:20.563 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [1261] milliseconds". Your server is now awaiting web requests.

 
 For further details about Smart Tomcat integration with IntelliJ see here: https://github.com/zengkid/SmartTomcat. 
 
 For a simple tutorial on setting up a new Java Servlet app with Tomcat, see here: https://github.com/KylePlummerBSCS/simple-servlets

## GET Endpoints
 Some of these endpoints expect data to be sent as a URL encoded form.
 
 
### GET /ping
 This endpoint is just used to verify connectivity. Responds with "PONG!".
 
### GET /flights
 flightService.getAllFlights
 

### GET /customer
customerService.getCustomers
 Parametes:
 - "First Name": String
 - "Last Name": String

### GET /flightlookup
flightService.flightLookup
 Parameters:
 - "departureLocation": String
 - "DestinationLocation": String


## POST Endpoints
Some of these endpoints expect data to be sent as a URL encoded form, some expect a JSON body.

### POST /purchase
ticketService.purchaseTicket
 Parameters:
 - "flight_id": String(UUID)
 - "customer_id": String(UUID)

### POST /createCustomer
customerService.createCustomer
 Parameters:
 - "First Name": String
 - "Last Name": String
 - "Customer Number": String(Parsable int)

### POST /createFlight
flightService.createFlight
 Parameter - JSON body:
```
{
    "flightNum":int,
    "departureLocation":"string",
    "destinationLocation":"string",
    "departureTime":"string(date or date/time)",
    "departureGate":"string",
    "destinationGate":"string"
}
```


## Application properties:
The jdbc.properties file should be located in your Resources Root directory. By default this directory is: /ssc/main/resources/ This file should not be included in github as it contains sensitive information. Thus it is included in .gitignore and not present here. You'll want to set it up yourself locally and make these details available to any environment the application is deployed to.
 The following fields are necessary to establish a connection to the database:

```
host=<hostname>
port=<port>
dbname=<database>
schemaname=<schema>
username=<database user username>
password=<database user password>
driver=<driver class name>
```
