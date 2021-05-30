package com.revature.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.dtos.FlightLookUp;
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

public class FlightController {

    public void getAllFlights(HttpServletRequest req, HttpServletResponse resp, Connection conn){
        try {

            List<Repository> flights =  Flight.query (conn, Flight.class);

            if(flights == null){
                resp.getWriter().println("Flights never showed up, so here we are");
            } else {
                flights.forEach(resp.getWriter()::println);
            }

        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    public void lookUpFlights(HttpServletRequest req, HttpServletResponse resp, Connection conn){
        ObjectMapper mapper = new ObjectMapper ();


        try {
            System.out.println ("Am I here");
            FlightLookUp lookUp = mapper.readValue (req.getInputStream (), FlightLookUp.class);
            System.out.println (lookUp);
            List<Repository> repos = Flight.query(conn, Flight.class);
            System.out.println (repos);
            List<Flight> flights = new ArrayList<> ();

            for (Repository repository:repos) {
                Flight flight = (Flight) repository;
                if(flight.getDepartureLocation ().equals(lookUp.getDeparturelocation ()) && flight.getDestinationLocation () .equals (lookUp.getDestinationlocation ())){
                    flights.add (flight);
                }
            }

            if(flights == null){
                resp.getWriter().println("Flights never showed up, so here we are");
            } else {
                flights.forEach(resp.getWriter()::println);
            }


        } catch (IOException e) {
            e.printStackTrace ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } catch (InvocationTargetException e) {
            e.printStackTrace ();
        } catch (InstantiationException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (NoSuchMethodException e) {
            e.printStackTrace ();
        }

    }

    public void info(HttpServletRequest req, HttpServletResponse resp, Connection conn) {
        UUID flight_id = UUID.fromString (req.getParameter ("flight_id"));
        List<Flight> flights = new ArrayList<> ();
        List<Repository> repositories = null;
        try {
            repositories = Ticket.query (conn, Ticket.class);

            for (Repository repo: repositories) {
                Flight flight = (Flight) repo;
                if(flight.getFlight_id () == flight_id ){
                    resp.getWriter ().println (flight);
                }
            }




        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } catch (InstantiationException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (NoSuchMethodException e) {
            e.printStackTrace ();
        } catch (InvocationTargetException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }


}
