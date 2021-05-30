package com.revature.airline;

import com.revature.airline.repos.Customer;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.utils.ConnectionFactory;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;
import java.util.UUID;

public class Driver {
//    public static void main(String[] args) {
//        try (FileReader jdbcPropFile = new FileReader("src/main/resources/jdbc.properties")) {
//            Properties props = new Properties();
//            props.load(jdbcPropFile);
//            Connection conn = ConnectionFactory.getConnection(
//                    props.getProperty("host"),
//                    Integer.parseInt(props.getProperty("port")),
//                    props.getProperty("dbname"),
//                    props.getProperty("schemaname"),
//                    props.getProperty("username"),
//                    props.getProperty("password"),
//                    props.getProperty("driver"));
//
//
//
//            Customer kyle = new Customer(conn, UUID.randomUUID(), "Kyle", "Plummer", 100001);
//            kyle.InitializeTable();
//
//            Flight flight = new Flight(conn, UUID.randomUUID(), 123, "Albany", "Las Vegas",
//                    "2021-05-25 11:25:00", "Gate 11A", "Gate H-21B");
//            flight.InitializeTable();
//
//            Ticket ticket = new Ticket(conn, UUID.randomUUID(), kyle.getCustomer_id(), flight.getFlight_id(), "14C");
//            ticket.InitializeTable();
//
//            kyle.save();
//            flight.save();
//            ticket.save();
//
//
//
//
//
//
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
