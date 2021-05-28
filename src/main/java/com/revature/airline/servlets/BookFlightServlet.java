package com.revature.airline.servlets;

import com.revature.airline.repos.Flight;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookFlightServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String destinationLocation = req.getParameter ("To");
        String departureLocation = req.getParameter ("From");


        //Flight flight = new Flight (departureLocation, destinationLocation);

        //TODO









    }
}
