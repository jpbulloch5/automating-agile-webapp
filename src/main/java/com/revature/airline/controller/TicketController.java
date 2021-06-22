package com.revature.airline.controller;

import com.revature.airline.repos.Ticket;
import eorm.utils.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
/**
 * These functions are called by the service layer and invoke the entity/repository CRUD functions.
 */
public class TicketController {

    public static void purchaseTickets(Ticket ticket)
            throws SQLException, IllegalAccessException {
            ticket.save();
    }

    /**
     * This method queries the flights table and filters out all but those matching the provided ID. These are then
     * counted and they represent the total seats already occupied. In our pretend airline, all planes have exactly 130 seats.
     * As long as there are less than 130 tickets sold, this returns that seat count and indicates that more tickets can be sold.
     * Otherwise returns -1 indicating that the flight is sold out.
     */
    public static int verifySpace(Connection conn, String flightID)
            throws SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {

        List<Repository> queryTickets = Ticket.query (conn, Ticket.class);
        long seatCount = queryTickets.stream()
                .map(obj -> (Ticket)obj)
                .filter(e -> e.getFlight_id().equals(UUID.fromString(flightID)))
                .count();

        if (seatCount < 130) {
            return (int)seatCount;
        } else {
            return -1;
        }

    }
}