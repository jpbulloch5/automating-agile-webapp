package com.revature.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.dtos.FlightDTO;
import com.revature.airline.dtos.FlightLookUp;
import com.revature.airline.repos.Customer;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import com.revature.airline.services.TicketService;
import com.revature.airline.utils.FileLogger;
import eorm.utils.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlightController {

    public void getAllFlights(HttpServletRequest req, HttpServletResponse resp, Connection conn)
            throws IOException, SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
            List<Repository> flights =  Flight.query(conn, Flight.class);

            if(flights == null){
                resp.getWriter().println("Flights never showed up, so here we are");
            } else {
                flights.forEach(resp.getWriter()::println);
            }
    }

    public void lookUpFlights(HttpServletRequest req, HttpServletResponse resp, Connection conn)
            throws IOException, SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {

        List<Repository> queryResults = Flight.query(conn, Flight.class);
        resp.getWriter().println("queryResults returns: " + queryResults.size() + "rows.");
        List<Flight> flights = queryResults.stream()
                .map(e -> (Flight)e)
                .filter(e -> e.getDepartureLocation().equals(req.getParameter("departureLocation")))
                .filter(e -> e.getDestinationLocation().equals(req.getParameter("DestinationLocation")))
                .collect(Collectors.toList());

        resp.setStatus(200);
        for (int i = 0; i < flights.size(); i++) {
            resp.getWriter().println(flights.get(i));
        }
    }

    public void info(HttpServletRequest req, HttpServletResponse resp, Connection conn)
            throws IOException, SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        UUID flight_id = UUID.fromString (req.getParameter ("flight_id"));
        List<Flight> flights = new ArrayList<>();
        List<Repository> repositories = null;
            repositories = Ticket.query (conn, Ticket.class);

        for (Repository repo: repositories) {
            Flight flight = (Flight) repo;
            if(flight.getFlight_id () == flight_id ){
                resp.getWriter ().println (flight);
            }
        }
        resp.setStatus(200);
    }

    public void createFlight(HttpServletRequest req, HttpServletResponse resp, Connection conn) throws IOException,
            SQLException, IllegalAccessException {
        ObjectMapper mapper = new ObjectMapper();
        FlightDTO flightDTO = mapper.readValue(req.getInputStream(), FlightDTO.class);
        Flight newFlight = new Flight(conn, UUID.randomUUID(), flightDTO.getFlightNum(),
                flightDTO.getDepartureLocation(), flightDTO.getDestinationLocation(),
                flightDTO.getDepartureTime(), flightDTO.getDepartureGate(), flightDTO.getDestinationGate());
        newFlight.save();
        resp.setStatus(201);
    }
}
