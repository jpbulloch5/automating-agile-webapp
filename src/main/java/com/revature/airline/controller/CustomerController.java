package com.revature.airline.controller;

import eorm.utils.Repository;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.airline.repos.Customer;



public class CustomerController {


    public static void createCustomer(Customer customer) throws SQLException, IllegalAccessException {
        customer.save();
    }

    public static List<Repository> getCustomers(Connection conn)
            throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchMethodException {
        return Customer.query(conn, Customer.class);
    }



}