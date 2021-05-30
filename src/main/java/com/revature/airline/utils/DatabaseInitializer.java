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
            FileLogger.getFileLogger().writeStringToFile("GETENV TEST - host: " + System.getenv("HOST")
                    + ", port: " + System.getenv("PORT")
                    + ", dbname: " + System.getenv("DBNAME")
                    + ", schema: " + System.getenv("SCHEMANAME")
                    + ", username: " + System.getenv("USERNAME")
                    + ", pass: " + System.getenv("PASSWORD")
                    + ", driver: " + System.getenv("DRIVER")
                    + ", test: " + System.getenv("JDBC_CONNECTION_STRING")
            );

            FileLogger.getFileLogger().writeStringToFile("GETPROPERTY TEST - host: " + System.getProperty("HOST")
                    + ", port: " + System.getProperty("PORT")
                    + ", dbname: " + System.getProperty("DBNAME")
                    + ", schema: " + System.getProperty("SCHEMANAME")
                    + ", username: " + System.getProperty("USERNAME")
                    + ", pass: " + System.getProperty("PASSWORD")
                    + ", driver: " + System.getProperty("DRIVER")
                    + ", JDBC: " + System.getProperty("JDBC_CONNECTION_STRING")
            );

            conn = ConnectionFactory.getConnection(
                    System.getProperty("HOST"),
                    Integer.parseInt(System.getProperty("PORT")),
                    System.getProperty("DBNAME"),
                    System.getProperty("SCHEMANAME"),
                    System.getProperty("USERNAME"),
                    System.getProperty("PASSWORD"),
                    System.getProperty("DRIVER"));


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
