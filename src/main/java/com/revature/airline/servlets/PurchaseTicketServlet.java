package com.revature.airline.servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchaseTicketServlet extends HttpServlet {
    Dispatcher dispatcher = new Dispatcher ();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.dataDispatch (req, resp);


    }


}