package com.revature.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eorm.utils.Repository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;
import com.revature.airline.dtos.CustomerInfo;
import com.revature.airline.repos.Customer;

public class CustomerController {


    public static void createCustomer(Customer customer) throws SQLException, IllegalAccessException {
        customer.save();
    }

    public static List<Repository> getCustomers(Connection conn)
            throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, IOException {
        //List<Repository> queryResults = Customer.query(conn, Customer.class);
        return Customer.query(conn, Customer.class);

//        List<Customer> customers = queryResults.stream()
//                .map(e -> (Customer)e)
//                .filter(e -> e.getLastName().equals(req.getParameter("Last Name")))
//                .filter(e -> e.getFirstName().equals(req.getParameter("First Name")))
//                .collect(Collectors.toList());

//        for (Customer customer : customers) {
//            resp.getWriter().println(customer.toString());
//        }
//        resp.setStatus(200);

    }



}