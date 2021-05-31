package com.revature.airline.services;

import com.revature.airline.controller.TicketController;
import com.revature.airline.dtos.TicketPurchaseDTO;
import com.revature.airline.repos.Ticket;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class TicketService {
    public int purchaseTicket(TicketPurchaseDTO ticketDTO, Connection conn) throws SQLException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {
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
