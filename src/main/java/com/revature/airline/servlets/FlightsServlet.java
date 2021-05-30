package com.revature.airline.servlets;

import com.revature.airline.repos.Flight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/flights")
public class FlightsServlet extends HttpServlet {
    private Dispatcher dispatcher= new Dispatcher();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        dispatcher.dataDispatch (req, resp);
    }
}
