package com.revature.airline.controller;

import com.revature.airline.repos.Flight;
import eorm.exceptions.DBConnectionException;
import eorm.utils.ConnectionFactory;
import eorm.utils.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FlightsController {

    public void getAllFlights(HttpServletRequest req, HttpServletResponse resp){
        try {
            Connection conn = ConnectionFactory.getConnection ("project0.cksippr4cmc5.us-east-1.rds.amazonaws.com", 5432, "postgres", "project1", "jfallon", "revature", "org.postgresql.Driver");
            List<Repository> flights =  Flight.query (conn, Flight.class);


            if(flights == null){
                resp.getWriter().println("Flights never showed up, so here we are");
            } else {
                flights.forEach(resp.getWriter()::println);

            }

        } catch (DBConnectionException | SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
            e.printStackTrace ();
        }



    }
}
