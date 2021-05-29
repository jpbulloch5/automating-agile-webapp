package com.revature.airline.services;

import com.revature.airline.repos.Ticket;
import eorm.utils.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TicketService {
    public void purchase(Connection conn){
        try {
            //TODO
            //Need to figure out how to access ticket field from Query of type Repository
            List<Repository> queryTickets = Ticket.query (conn, Ticket.class);
            List<Repository> tickets = queryTickets;



        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } catch (InstantiationException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (NoSuchMethodException e) {
            e.printStackTrace ();
        } catch (InvocationTargetException e) {
            e.printStackTrace ();
        }





    }

}
