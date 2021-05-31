package com.revature.airline.dtos;

import eorm.annotations.Column;
import eorm.enums.SQLType;

import java.util.UUID;

public class FlightDTO {
    private int flightNum;
    private String departureLocation;
    private String destinationLocation;
    private String departureTime;
    private String departureGate;
    private String destinationGate;

    public FlightDTO() {
        super();
    }

    public int getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(String departureGate) {
        this.departureGate = departureGate;
    }

    public String getDestinationGate() {
        return destinationGate;
    }

    public void setDestinationGate(String destinationGate) {
        this.destinationGate = destinationGate;
    }
}


