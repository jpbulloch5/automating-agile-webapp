package com.revature.airline.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.repos.Flight;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class FlightSerializerTest {
    //    public Flight(Connection conn, UUID id, int flightNum, String departureLocation, String destinationLocation,
    //                  String departureTime, String departureGate, String destinationGate) {
    @Test
    public void test_flightSerializationToJSON() throws JsonProcessingException {
        Connection mockConn = mock(Connection.class);
        UUID mockUUID = UUID.fromString("2427d961-c9d3-4717-b28c-ec684899812a");
        String mockFirstName = "firstName";
        String mockLastName = "lastName";
        Integer mockNum = 5;
        String mockDepartureLocation = "departureLocation";
        String mockDestinationLocation = "destinationLocation";
        String mockDepartureTime = "departureTime";
        String mockDepartureGate = "departureGate";
        String mockDestinationGate = "destinationGate";
        Flight flight = new Flight(mockConn, mockUUID, mockNum, mockDepartureLocation, mockDestinationLocation,
                mockDepartureTime, mockDepartureGate, mockDestinationGate);
        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(
                "{\"flight_id\":\"" + mockUUID + "\"," +
                        "\"flightNum\":" + mockNum + "," +
                        "\"departureLocation\":\"" + mockDepartureLocation + "\"," +
                        "\"destinationLocation\":\"" + mockDestinationLocation + "\"," +
                        "\"departureTime\":\"" + mockDepartureTime + "\"," +
                        "\"departureGate\":\"" + mockDepartureGate + "\"," +
                        "\"destinationGate\":\"" + mockDestinationGate + "\"}",
                mapper.writeValueAsString(flight));
    }
}

//[{"flight_id":"e068aa62-2b14-4f61-967d-425665b40120","flightNum":2000,"departureLocation":"Jonestown","destinationLocation":"Washington DC","departureTime":"2021-05-30 06:35:00","departureGate":"Gate 1","destinationGate":"Gate B"}]