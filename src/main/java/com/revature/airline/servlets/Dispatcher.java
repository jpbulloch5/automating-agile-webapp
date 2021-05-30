package com.revature.airline.servlets;

import com.revature.airline.controller.CustomerController;
import com.revature.airline.controller.FlightController;
import com.revature.airline.controller.TicketController;
import eorm.exceptions.DBConnectionException;
import eorm.utils.ConnectionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Dispatcher {
    Connection conn;

    {
        try {
            conn = ConnectionFactory.getConnection ("project0.cksippr4cmc5.us-east-1.rds.amazonaws.com", 5432, "postgres", "project1", "jfallon", "revature", "org.postgresql.Driver");
        } catch (DBConnectionException e) {
            e.printStackTrace ();
        }
    }

    FlightController flightController = new FlightController ();
    TicketController ticketController = new TicketController ();
    CustomerController customerController = new CustomerController ();

    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp)throws IOException {

        switch(req.getRequestURI ()){
            case"/webapp/flights":
                flightController.getAllFlights(req, resp, this.conn);
                break;
            case"/webapp/purchase":
                ticketController.purchaseTickets (req, resp, this.conn);
                break;
            case"/webapp/customer":
                customerController.createCustomer (req, resp, this.conn);
                break;
            case"/webapp/lookup":
                flightController.lookUpFlights (req, resp, this.conn);
                break;
            case"/webapp/details":
                flightController.info (req, resp, this.conn);
            default:
                resp.getWriter().print(req.getRequestURI ());


        }
    }
}
