package com.revature.airline.servlets;

import com.revature.airline.utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/details")
public class CustomerFlightDetails extends HttpServlet {
    private Dispatcher dispatcher= new Dispatcher();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
            dispatcher.dataDispatch (req, resp,"details");
    }
}
