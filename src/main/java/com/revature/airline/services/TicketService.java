package com.revature.airline.services;

import com.revature.airline.controller.TicketController;
import com.revature.airline.dtos.TicketPurchaseDTO;
import com.revature.airline.repos.Ticket;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 * This service layer class handles all requests involving the ticket resource.
 */
public class TicketService {
    /**
     * This method checks to verify there is space available on the flight,
     * then marshals a ticket entity/repository and sends it to the controller to be persisted.
     * @param ticketDTO - a data object representing the ticket resource.
     * @param conn - a connection object connected to the datasource.
     * @return - either a 201 successful status code or a 406 unsuccessful.
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public int purchaseTicket(TicketPurchaseDTO ticketDTO, Connection conn) throws SQLException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int seatNum = TicketController.verifySpace(conn, ticketDTO.getFlightID());
        if (seatNum != -1) {
            Ticket ticket = new Ticket(conn, UUID.randomUUID(), UUID.fromString(ticketDTO.getCustomerID()),
                    UUID.fromString(ticketDTO.getFlightID()), seatNum);
            TicketController.purchaseTickets(ticket);
            return 201;
        } else {
            return 406;
        }
    }
}
