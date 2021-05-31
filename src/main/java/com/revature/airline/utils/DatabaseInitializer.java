package com.revature.airline.utils;

import com.revature.airline.repos.Customer;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.exceptions.DBConnectionException;
import eorm.exceptions.MalformedTableException;
import eorm.utils.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public abstract class DatabaseInitializer {
    private static boolean initialized = false;
    private static Connection conn;


    public static void init() throws SQLException, MalformedTableException, DBConnectionException {
        if (!initialized) {
            conn = ConnectionFactory.getConnection(
                    System.getProperty("HOST"),
                    Integer.parseInt(System.getProperty("PORT")),
                    System.getProperty("DBNAME"),
                    System.getProperty("SCHEMANAME"),
                    System.getProperty("USERNAME"),
                    System.getProperty("PASSWORD"),
                    System.getProperty("DRIVER"));

//            conn = ConnectionFactory.getConnection(
//               "database.ciwpi6yisnng.us-east-2.rds.amazonaws.com",
//                    5432,
//                    "postgres",
//                    "FallonAir",
//                    "appuser",
//                    "4ppp4ssDontGoChasingWaterfalls",
//                    "org.postgresql.Driver");


            Flight flight = new Flight(conn);
            Customer customer = new Customer(conn);
            Ticket ticket = new Ticket(conn);

            flight.initializeTable();
            customer.initializeTable();
            ticket.initializeTable();

            initialized = true;
        }
    }

    public static Connection getconnection() throws SQLException, MalformedTableException, DBConnectionException, IOException {
        if(!initialized) {
            init();
        }
        return conn;
    }
}
