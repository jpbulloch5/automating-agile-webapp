package com.revature.airline.controller;

import com.revature.airline.repos.Customer;
import com.revature.airline.utils.FileLogger;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.utils.Repository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerController {

    public void createCustomer(HttpServletRequest req, HttpServletResponse resp, Connection conn) {
        String firstName = req.getParameter ("First Name");
        String lastName = req.getParameter ("Last Name");
        int customerNum = Integer.parseInt (req.getParameter ("Customer Number"));

        Customer customer = new Customer (conn, UUID.randomUUID(), firstName, lastName, customerNum);
        try {
            customer.save();
            resp.setStatus(201);
        } catch (IllegalAccessException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        } catch (SQLException throwables) {
            FileLogger.getFileLogger().writeExceptionToFile(throwables);
        } catch (NullPointerException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

    }



}