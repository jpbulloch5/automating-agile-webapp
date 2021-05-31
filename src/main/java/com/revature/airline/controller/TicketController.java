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

public class TicketController {

    public static void purchaseTickets(Ticket ticket)
            throws SQLException, IllegalAccessException {
            ticket.save();
    }

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