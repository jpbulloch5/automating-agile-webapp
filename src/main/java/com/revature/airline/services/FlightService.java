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

/**
 * this service layer class handles all requests involving the flights resource.
 */
public class FlightService {

    /**
     * This retrieves all flights from the database and marshals them into a JSON list.
     * @param conn - a connection object connected to the datasource.
     * @return - JSON string representing a list of flight objects
     * @throws SQLException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public String getAllFlights(Connection conn)
            throws SQLException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Flight> flightList = FlightController.getAllFlights(conn).stream()
                .map(e -> (Flight)e)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(flightList);
    }

    /**
     * This method looks up a specific flight based on departure and destination cities.
     * We return the whole table and filter the data as a stream. In a perfect world we wouldn't
     * return the entire table to be sorted by the app, but we decided this was a simpler solution
     * than designing the ORM to create more complex queries.
     * @param parameterMap - a map of parameters pulled from the HTTP request web form parameters.
     * @param conn - a connection object connected to the datasource.
     * @return - JSON string representing a list of flight objects
     * @throws SQLException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
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


    /**
     * This method marshals a Flight entity/repository from a DTO and send it to the controller to be persisted.
     * @param flightDTO - a data object representing the flight resource.
     * @param conn - a connection object connected to the datasource.
     * @return - successful status code 201.
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public int createFlight(FlightDTO flightDTO, Connection conn) throws SQLException, IllegalAccessException {
        Flight newflight = new Flight(conn, UUID.randomUUID(), flightDTO.getFlightNum(), flightDTO.getDepartureLocation(),
                flightDTO.getDestinationLocation(), flightDTO.getDepartureTime(), flightDTO.getDepartureGate(), flightDTO.getDestinationGate());
        FlightController.createFlight(newflight);
        return 201;

    }
}
