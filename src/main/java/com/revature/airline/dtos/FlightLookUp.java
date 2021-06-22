package com.revature.airline.dtos;
/**
 * These DTOs are super simple java objects used by the service layer. This data goes to an entity/repository
 * object to be persisted in the database, or is marshalled as a JSON string by jackson to be sent over HTTP.
 */
public class FlightLookUp {
    private String departurelocation;
    private String destinationlocation;

    public FlightLookUp() {
        super();
    }

    public String getDeparturelocation() {
        return departurelocation;
    }

    public void setDeparturelocation(String departurelocation) {
        this.departurelocation = departurelocation;
    }

    public String getDestinationlocation() {
        return destinationlocation;
    }

    public void setDestinationlocation(String destinationlocation) {
        this.destinationlocation = destinationlocation;
    }
}
