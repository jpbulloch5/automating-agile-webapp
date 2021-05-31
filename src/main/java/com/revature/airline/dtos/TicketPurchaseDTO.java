package com.revature.airline.dtos;

import java.util.UUID;

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
