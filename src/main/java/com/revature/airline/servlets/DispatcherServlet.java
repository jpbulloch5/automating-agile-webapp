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
            conn = DatabaseInitializer.getConnection();
        } catch (Exception e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        flightService = new FlightService();
        ticketService = new TicketService();
        customerService = new CustomerService();
    }


    public void testInit(Connection c, FlightService fs, TicketService ts, CustomerService cs) {
        conn = c;
        flightService = fs;
        ticketService = ts;
        customerService = cs;
    }


    /**
     * This servlet handles and dispatches all GET requests to the appropriate service.
     * Parameter names are queried and mapped, the map is passed to services which require parameters.
     * Exceptions are thrown back up to here and logged. Returned data is passed back to here
     * and written to response along with status code.
     * @param req - HttpServletRequest provided by requester
     * @param resp - HttpServletResponse to be returned to requester
     * @throws ServletException
     * @throws IOException
     */
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
                case "ping":
                    resp.getWriter().println("PONG!");
                    resp.setStatus(200);
                    break;
                default:
                    resp.setStatus(501);
            }

        } catch(Exception e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }


    }

    /**
     * This servlet handles and dispatches all PUT requests to the appropriate service. Instead of parameter lists,
     * requests are parsed as input streams and mapped to DTOs which are passed to the service layer.
     * Exceptions are thrown back up to here and logged. While it is proper practice to return a copy of
     * new/altered resources along with successful status codes, we didn't know that yet.
     * @param req - HttpServletRequest provided by requester
     * @param resp - HttpServletResponse to be returned to requester
     * @throws ServletException
     * @throws IOException
     */
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
                default:
                    resp.setStatus(501);
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }


    /**
     * This function simply splits the URI into string tokens using "/" as a delimiter. It returns the last element
     * which is the URI section after the final "/". Depending on environment (local tomcat or AWS tomact) the
     * webserver will include additional path sections. The webapp is tooled to only ever utilize
     * whatever the final URI section is.
     * @param req
     * @return
     */
    private String parseURI(HttpServletRequest req) {
        String string = req.getRequestURI();
        String[] parsedStrings = string.split("/");
        return parsedStrings[parsedStrings.length-1];
    }

}
