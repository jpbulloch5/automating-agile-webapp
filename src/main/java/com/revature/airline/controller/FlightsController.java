package com.revature.airline.controller;

import com.revature.airline.repos.Flight;
import com.revature.airline.services.TicketService;
import eorm.utils.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class FlightsController {

    public void getAllFlights(HttpServletRequest req, HttpServletResponse resp, Connection conn){
        try {

            List<Repository> flights =  Flight.query (conn, Flight.class);

            if(flights == null){
                resp.getWriter().println("Flights never showed up, so here we are");
            } else {
                flights.forEach(resp.getWriter()::println);
            }

        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void lookUpFlights(HttpServletRequest req, HttpServletResponse resp){

    }

    public void purchaseTickets(HttpServletRequest req, HttpServletResponse resp, Connection conn){
        UUID customer_id = UUID.fromString (req.getParameter ("customer_id"));
        UUID flight_id = UUID.fromString (req.getParameter ("flight_id"));
        UUID ticket_id = UUID.randomUUID ();

        TicketService ticketService = new TicketService ();

        ticketService.purchase (conn);


    }
}
