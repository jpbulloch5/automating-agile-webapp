package com.revature.airline.controller;

import com.revature.airline.repos.Customer;
import com.revature.airline.utils.FileLogger;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.utils.Repository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerController {

    public void createCustomer(HttpServletRequest req, HttpServletResponse resp, Connection conn)
            throws SQLException, IllegalAccessException {
        String firstName = req.getParameter ("First Name");
        String lastName = req.getParameter ("Last Name");
        int customerNum = Integer.parseInt (req.getParameter ("Customer Number"));

        Customer customer = new Customer (conn, UUID.randomUUID(), firstName, lastName, customerNum);

        customer.save();
        resp.setStatus(201);
    }

    public void getCustomers(HttpServletRequest req, HttpServletResponse resp, Connection conn)
            throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, IOException {
        List<Repository> queryResults = Customer.query(conn, Customer.class);

        List<Customer> customers = queryResults.stream()
                .map(e -> (Customer)e)
                .filter(e -> e.getLastName().equals(req.getParameter("Last Name")))
                .filter(e -> e.getFirstName().equals(req.getParameter("First Name")))
                .collect(Collectors.toList());

        for (Customer customer : customers) {
            resp.getWriter().println(customer.toString());
        }
        resp.setStatus(200);

    }



}