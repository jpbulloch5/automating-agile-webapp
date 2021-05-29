package com.revature.airline.servlets;

import com.revature.airline.controller.FlightsController;
import eorm.exceptions.DBConnectionException;
import eorm.utils.ConnectionFactory;

import javax.servlet.http.HttpServlet;
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

    FlightsController controller = new FlightsController ();

    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp)throws IOException {

        switch(req.getRequestURI ()){
            case"/webapp/flight":
                controller.getAllFlights(req, resp, this.conn);
                break;
            case"/webapp/purchase":
                controller.purchaseTickets (req, resp, this.conn);
        }
    }
}
