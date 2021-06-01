package com.revature.airline.utils;

import com.revature.airline.repos.Customer;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.annotations.Table;
import eorm.exceptions.DBConnectionException;
import eorm.exceptions.MalformedTableException;
import eorm.utils.ConnectionFactory;
import eorm.utils.Repository;
import eorm.utils.TableInitializer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;


public class DatabaseInitializer {
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


            Flight flight = new Flight(conn);
            Customer customer = new Customer(conn);
            Ticket ticket = new Ticket(conn);

//            flight.initializeTable();
//            customer.initializeTable();
//            ticket.initializeTable();

            TreeMap<String, Repository> tableMap = new TreeMap<>();
            tableMap.put("ticket", ticket);//Requires both customer and flight FK relations
            tableMap.put("customer", customer);
            tableMap.put("flight", flight);
            TableInitializer.initializeTableList(tableMap, conn);



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
