package com.revature.airline.controller;

import com.revature.airline.repos.Customer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class CustomerController {

    public void createCustomer(HttpServletRequest req, HttpServletResponse resp, Connection conn)  {
        String firstName = req.getParameter ("First Name");
        String lastName = req.getParameter ("Last Name");
        int customerNum = Integer.parseInt (req.getParameter ("Customer Number"));

        Customer customer = new Customer (conn, UUID.randomUUID (), firstName, lastName, customerNum);
        try {
            System.out.println ("I got hit");
            customer.save ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }

    }
}