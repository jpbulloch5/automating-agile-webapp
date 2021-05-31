package com.revature.airline.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.controller.FlightController;
import com.revature.airline.dtos.FlightDTO;
import com.revature.airline.repos.Flight;
import eorm.utils.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


public class FlightService {
    public String getAllFlights(Connection conn)
            throws SQLException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Flight> flightList = FlightController.getAllFlights(conn).stream()
                .map(e -> (Flight)e)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(flightList);
    }

    public String flightLookup(Map<String, String> parameterMap, Connection conn) throws SQLException, IOException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Repository> queryResults = FlightController.lookUpFlights(conn);
        List<Flight> flights = queryResults.stream()
                .map(e -> (Flight)e)
                .filter(e -> e.getDepartureLocation().equals(parameterMap.get("departureLocation")))
                .filter(e -> e.getDestinationLocation().equals(parameterMap.get("DestinationLocation")))
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(flights);
    }


    public int createFlight(FlightDTO flightDTO, Connection conn) throws SQLException, IllegalAccessException {
        Flight newflight = new Flight(conn, UUID.randomUUID(), flightDTO.getFlightNum(), flightDTO.getDepartureLocation(),
                flightDTO.getDestinationLocation(), flightDTO.getDepartureTime(), flightDTO.getDepartureGate(), flightDTO.getDestinationGate());
        FlightController.createFlight(newflight);
        return 201;

    }
}
