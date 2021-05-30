package com.revature.airline.controller;

import com.revature.airline.repos.Ticket;
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

    public void purchaseTickets(HttpServletRequest req, HttpServletResponse resp, Connection conn) {
        UUID customer_id = UUID.fromString (req.getParameter ("customer_id"));
        UUID flight_id = UUID.fromString (req.getParameter ("flight_id"));

        Ticket newTicket = verifySpace (conn, flight_id, resp);
        if(newTicket != null){
            newTicket.setCustomer_id (customer_id);
            newTicket.setFlight_id (flight_id);
            newTicket.setTicket_id (UUID.randomUUID ());
            try {
                newTicket.save ();
            } catch (IllegalAccessException e) {
                e.printStackTrace ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
        }

    }

    public Ticket verifySpace(Connection conn, UUID flight_id, HttpServletResponse resp) {
        try {
            List<Repository> queryTickets = Ticket.query (conn, Ticket.class);
            List<Ticket> tickets = new ArrayList<> ();

            for (Repository repository : queryTickets) {
                Ticket ticket = (Ticket) repository;
                if (flight_id == ticket.getFlight_id ()) {
                    tickets.add (ticket);
                }
            }

            if (tickets.size () > 130) {
                try {
                    resp.getWriter ().println ("Flight sold out.");
                } catch (IOException e) {
                    e.printStackTrace ();
                }
                return null;
            } else {
                Ticket newTicket = new Ticket(conn);
                newTicket.setSeat (tickets.size ());
                return newTicket;
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } catch (InvocationTargetException e) {
            e.printStackTrace ();
        } catch (InstantiationException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (NoSuchMethodException e) {
            e.printStackTrace ();
        }
        return null;

    }
}