# team_e_p1_webapp
## Ticket purchasing microservice

This webapp exposes a small number of endpoints to serve an airline ticketing kiosk. It allows the UI running on the kiosk
to display flights, and purchase tickets. To facilitate a demonstration of this functionality there are also endpoints for
looking up customers and creating flights.


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
The following fields are necessary to establish a connection to the database.

```
host=<hostname>
port=<port>
dbname=<database>
schemaname=<schema>
username=<database user username>
password=<database user password>
driver=<driver class name>
```
