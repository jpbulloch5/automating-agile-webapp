package com.revature.airline.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.dtos.CustomerInfo;
import com.revature.airline.dtos.FlightDTO;
import com.revature.airline.dtos.TicketPurchaseDTO;
import com.revature.airline.services.CustomerService;
import com.revature.airline.services.FlightService;
import com.revature.airline.services.TicketService;
import com.revature.airline.utils.DatabaseInitializer;
import com.revature.airline.utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
    private static Connection conn;
    private static FlightService flightService;
    private static TicketService ticketService;
    private static CustomerService customerService;

    @Override
    public void init() {
        try {
            conn = DatabaseInitializer.getconnection();
        } catch (Exception e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        flightService = new FlightService();
        ticketService = new TicketService();
        customerService = new CustomerService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = parseURI(req);
        Map<String, String> parameterMap = new HashMap<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String element = parameterNames.nextElement();
            parameterMap.put(element, req.getParameter(element));
        }


        try {
            switch (target) {
                case "flights":
                    String allFlights = flightService.getAllFlights(conn);
                    resp.setStatus(200);
                    resp.getWriter().println(allFlights);
                    break;
                case "customer":
                    String customers = customerService.getCustomers(parameterMap, conn);
                    resp.setStatus(200);
                    resp.getWriter().println(customers);
                    break;
                case "flightLookup":
                    String flights = flightService.flightLookup(parameterMap, conn);
                    resp.setStatus(200);
                    resp.getWriter().println(flights);
                    break;
            }

        } catch(Exception e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = parseURI(req);
        ObjectMapper mapper = new ObjectMapper();
        try{
            switch (target) {
                case "purchase":
                    TicketPurchaseDTO ticketDTO = mapper.readValue(req.getInputStream(), TicketPurchaseDTO.class);
                    resp.setStatus(ticketService.purchaseTicket(ticketDTO, conn));
                    if(resp.getStatus() == 406) {
                        resp.getWriter().println("Flight is sold out.");
                    } else {
                        resp.getWriter().println("Ticket sold.");
                    }
                    break;
                case "createCustomer":
                    CustomerInfo customerDTO = mapper.readValue(req.getInputStream(), CustomerInfo.class);
                    resp.setStatus(customerService.createCustomer(customerDTO, conn));
                    break;
                case "createFlight":
                    FlightDTO flightDTO = mapper.readValue(req.getInputStream(), FlightDTO.class);
                    resp.setStatus(flightService.createFlight(flightDTO, conn));
                    break;
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }


    private String parseURI(HttpServletRequest req) {
        String string = req.getRequestURI();
        String[] parsedStrings = string.split("/");
        return parsedStrings[parsedStrings.length-1];
    }

}
