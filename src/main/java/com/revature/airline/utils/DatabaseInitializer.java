package com.revature.airline.utils;

import com.revature.airline.repos.Customer;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.exceptions.DBConnectionException;
import eorm.exceptions.MalformedTableException;
import eorm.utils.ConnectionFactory;
import eorm.utils.Repository;
import eorm.utils.TableInitializer;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TreeMap;


public class DatabaseInitializer {
    private static boolean initialized = false;
    private static Connection conn;


    public static void init() throws SQLException, MalformedTableException, DBConnectionException {
        if (!initialized) {
            /**
             * This section is for if the app is running in an AWS tomcat environment, grabs datasource connection
             * info from the system variables. Uncomment and replace other datasource block with this if necessary.
             * System.getProperty gets a variable from the JVM environment. System.getEnv gets it from the OS. In an AWS
             * tomcat environment use getProperty to get variables assigned with the environment config in
             * Elastic Beanstalk. In a non-tomcat environment use System.getEnv to get the same configured variables.
             */
//            conn = ConnectionFactory.getConnection(
//                    System.getProperty("HOST"),
//                    Integer.parseInt(System.getProperty("PORT")),
//                    System.getProperty("DBNAME"),
//                    System.getProperty("SCHEMANAME"),
//                    System.getProperty("USERNAME"),
//                    System.getProperty("PASSWORD"),
//                    System.getProperty("DRIVER"));

            /**
             * This section is for running locally. It pulls the datasource connection info from a file
             * seen in the FileReader parameters. A description of that file can be found in the readme.
             * File must be located in the resources root folder in order for getresourcesAsStream() to locate it.
             */
            try {
                InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
                Properties props = new Properties();
                props.load(resourceStream);
                conn = ConnectionFactory.getConnection(
                        props.getProperty("host"),
                        Integer.parseInt(props.getProperty("port")),
                        props.getProperty("dbname"),
                        props.getProperty("schemaname"),
                        props.getProperty("username"),
                        props.getProperty("password"),
                        props.getProperty("driver"));
                } catch(IOException e) {
                    FileLogger.getFileLogger().writeExceptionToFile(e);
                }


            Flight flight = new Flight(conn);
            Customer customer = new Customer(conn);
            Ticket ticket = new Ticket(conn);

            TreeMap<String, Repository> tableMap = new TreeMap<>();
            tableMap.put("ticket", ticket);//Requires both customer and flight FK relations
            tableMap.put("customer", customer);
            tableMap.put("flight", flight);
            TableInitializer.initializeTableList(tableMap, conn);

            initialized = true;
        }
    }

    public static Connection getConnection() throws SQLException, MalformedTableException, DBConnectionException, IOException {
        if(!initialized) {
            init();
        }
        return conn;
    }
}
