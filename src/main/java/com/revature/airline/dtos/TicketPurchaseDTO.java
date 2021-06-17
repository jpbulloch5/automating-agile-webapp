package com.revature.airline.dtos;

import java.util.UUID;
/**
 * These DTOs are super simple java objects used by the service layer. This data goes to an entity/repository
 * object to be persisted in the database, or is marshalled as a JSON string by jackson to be sent over HTTP.
 */
public class TicketPurchaseDTO {
    private String customerID;
    private String flightID;

    public TicketPurchaseDTO() {
        super();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }
}
