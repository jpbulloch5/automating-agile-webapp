package com.revature.airline.pojo;

public class Flight {
    int flightNumber;
    String departureLocation;
    String destinationLocation;

    public Flight(String departureLocation, String destinationLocation) {
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
    }
}
