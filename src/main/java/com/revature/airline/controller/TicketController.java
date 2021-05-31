package com.revature.airline.controller;

import com.revature.airline.repos.Ticket;
import com.revature.airline.services.TicketService;
import com.revature.airline.utils.FileLogger;
import eorm.utils.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketController {

    public void purchaseTickets(HttpServletRequest req, HttpServletResponse resp, Connection conn)
            throws SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, IOException {
        UUID customer_id = UUID.fromString (req.getParameter ("customer_id"));
        UUID flight_id = UUID.fromString (req.getParameter ("flight_id"));

        Ticket newTicket = verifySpace (conn, flight_id, resp);
        if(newTicket != null){
            newTicket.setCustomer_id (customer_id);
            newTicket.setFlight_id (flight_id);
            newTicket.setTicket_id (UUID.randomUUID ());

            newTicket.save ();

        }

    }

    public Ticket verifySpace(Connection conn, UUID flight_id, HttpServletResponse resp)
            throws SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, IOException {

        List<Repository> queryTickets = Ticket.query (conn, Ticket.class);

        //trying out these newfangled streams
        long seatCount = queryTickets.stream()
                .map(obj -> (Ticket)obj)
                .filter(e -> e.getFlight_id().equals(flight_id))
                .count();

        if (seatCount < 130) {
            Ticket newTicket = new Ticket(conn);
            newTicket.setSeat((int)seatCount);
            return newTicket;
        } else {
            resp.getWriter().print("Flight sold out.");
        }
        return null;

//
//        for (Repository repository : queryTickets) {
//            Ticket ticket = (Ticket) repository;
//            if (flight_id.equals(ticket.getFlight_id ())) {
//                tickets.add (ticket);
//            }
//        }
//
//        if (tickets.size () > 130) {
//            try {
//                resp.getWriter ().println ("Flight sold out.");
//            } catch (IOException e) {
//                FileLogger.getFileLogger().writeExceptionToFile(e);
//            }
//            return null;
//        } else {
//            Ticket newTicket = new Ticket(conn);
//            newTicket.setSeat(tickets.size());
//            return newTicket;
//        }
    }
}