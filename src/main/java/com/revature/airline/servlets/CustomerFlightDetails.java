package com.revature.airline.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/details")
public class CustomerFlightDetails extends HttpServlet {
    private DispatcherServlet dispatcherServlet = new DispatcherServlet();

//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
//            dispatcherServlet.dataDispatch (req, resp,"details");
//    }
}
