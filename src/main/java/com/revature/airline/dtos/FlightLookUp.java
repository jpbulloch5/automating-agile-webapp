package com.revature.airline.dtos;

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
