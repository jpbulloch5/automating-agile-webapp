package com.revature.airline.servlets;

import com.revature.airline.controller.CustomerController;
import com.revature.airline.controller.FlightController;
import com.revature.airline.controller.TicketController;
import com.revature.airline.utils.FileLogger;
import eorm.exceptions.DBConnectionException;
import eorm.utils.ConnectionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Dispatcher {
    Connection conn;

    public Dispatcher() {
        //REMOVE ME LATER
        FileLogger.getFileLogger().writeExceptionToFile(new Exception("TEST EXCEPTION TO FORCE LOGGER TO SPAWN NEW FILE"));

        try {
            conn = ConnectionFactory.getConnection ("project0.cksippr4cmc5.us-east-1.rds.amazonaws.com", 5432, "postgres", "project1", "jfallon", "revature", "org.postgresql.Driver");
        } catch (DBConnectionException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    FlightController flightController = new FlightController ();
    TicketController ticketController = new TicketController ();
    CustomerController customerController = new CustomerController ();

    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp, String target)throws IOException {

        switch(target){
            case"flights":
                flightController.getAllFlights(req, resp, conn);
                break;
            case"purchase":
                ticketController.purchaseTickets (req, resp, conn);
                break;
            case"customer":
                customerController.createCustomer (req, resp, conn);
                break;
            case"lookup":
                flightController.lookUpFlights (req, resp, conn);
                break;
            case"details":
                flightController.info (req, resp, conn);
                break;
            default:
                resp.setStatus(501);
        }
    }
}
