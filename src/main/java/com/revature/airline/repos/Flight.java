package com.revature.airline.repos;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.revature.airline.serializers.FlightSerializer;
import eorm.annotations.Column;
import eorm.annotations.Table;
import eorm.enums.SQLType;
import eorm.utils.Repository;

import java.sql.Connection;
import java.util.UUID;

@JsonSerialize(using = FlightSerializer.class)
@Table(tableName = "flight")
public class Flight extends Repository {
    @Column(type = SQLType.UUID, primaryKey = true)
    private UUID flight_id;

    @Column(type = SQLType.INT)
    private int flightNum;

    @Column(type = SQLType.VARCHAR)
    private String departureLocation;

    @Column(type = SQLType.VARCHAR)
    private String destinationLocation;

    @Column(type = SQLType.VARCHAR)
    private String departureTime;

    @Column(type = SQLType.VARCHAR)
    private String departureGate;

    @Column(type = SQLType.VARCHAR)
    private String destinationGate;


    public Flight(Connection conn) {
        super(conn);
    }

    public Flight(Connection conn, UUID id, int flightNum, String departureLocation, String destinationLocation,
                  String departureTime, String departureGate, String destinationGate) {
        super(conn);
        this.flight_id = id;
        this.flightNum = flightNum;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.departureTime = departureTime;
        this.departureGate = departureGate;
        this.destinationGate = destinationGate;
    }

    public UUID getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(UUID flight_id) {
        this.flight_id = flight_id;
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

    @Override
    public String toString() {
        return "{" +
                "\n\t\"flight_id\":\"" + flight_id + "\"" +
                ",\n\t\"flightNum\":" + flightNum +
                ",\n\t\"departureLocation\":\"" + departureLocation + "\"" +
                ",\n\t\"destinationLocation\":\"" + destinationLocation + "\"" +
                ",\n\t\"departureTime\":\"" + departureTime + "\"" +
                ",\n\t\"departureGate\":\"" + departureGate + "\"" +
                ",\n\t\"destinationGate\":\"" + destinationGate + "\"" +
                "\n}\n";
    }


}
