package com.revature.airline;

import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.exceptions.DBConnectionException;
import eorm.exceptions.MalformedTableException;
import eorm.utils.ConnectionFactory;
import eorm.utils.TableInitializer;

import java.sql.Connection;
import java.sql.SQLException;

public class AppDriver {
    public static void main(String[] args) {
        try {
            Connection conn = ConnectionFactory.getConnection ("project0.cksippr4cmc5.us-east-1.rds.amazonaws.com", 5432, "postgres", "project1", "jfallon", "revature", "org.postgresql.Driver");
            Flight flight = new Flight (conn);
            TableInitializer.initializeTable (flight);
            Ticket ticket = new Ticket (conn);
            TableInitializer.initializeTable (ticket);



        } catch (DBConnectionException e) {
            e.printStackTrace ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } catch (MalformedTableException e) {
            e.printStackTrace ();
        }


    }
}
