package com.revature.airline.servlets;

import com.revature.airline.controller.FlightsController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {
    FlightsController controller = new FlightsController ();

    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        switch(req.getRequestURI ()){
            case"/webapp/flight":
                controller.getAllFlights(req, resp);
                break;
        }
    }
}
