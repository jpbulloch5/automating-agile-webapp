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


public class DatabaseInitializer {
    private static boolean initialized = false;
    private static Connection conn;


    public static void init() throws SQLException, MalformedTableException, DBConnectionException {
        if (!initialized) {
            //remove me later
            FileLogger.getFileLogger().writeStringToFile("host: " + System.getenv("HOST")
                    + ", port: " + System.getenv("PORT")
                    + ", dbname: " + System.getenv("DBNAME")
                    + ", schema: " + System.getenv("SCHEMANAME")
                    + ", username: " + System.getenv("USERNAME")
                    + ", pass: " + System.getenv("PASSWORD")
                    + ", driver: " + System.getenv("DRIVER")
                    + ", test: " + System.getenv("JDBC_CONNECTION_STRING")
            );

            conn = ConnectionFactory.getConnection(
                    System.getenv("HOST"),
                    Integer.parseInt(System.getenv("PORT")),
                    System.getenv("DBNAME"),
                    System.getenv("SCHEMANAME"),
                    System.getenv("USERNAME"),
                    System.getenv("PASSWORD"),
                    System.getenv("DRIVER"));


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
