package com.revature.airline.controller;

import com.revature.airline.repos.Flight;
import com.revature.airline.services.TicketService;
import com.revature.airline.utils.FileLogger;
import eorm.utils.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
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

    public void lookUpFlights(HttpServletRequest req, HttpServletResponse resp){

    }


}
