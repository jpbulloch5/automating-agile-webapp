package com.revature.airline.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.controller.CustomerController;
import com.revature.airline.dtos.CustomerInfo;
import com.revature.airline.repos.Customer;
import eorm.utils.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerService {
    public String getCustomers(Map<String, String> parameterMap, Connection conn) throws SQLException, IOException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Repository> queryresults = CustomerController.getCustomers(conn);
        List<Customer> customerList = queryresults.stream()
                .map(e -> (Customer)e)
                .filter(e -> e.getLastName().equals(parameterMap.get("Last Name")))
                .filter(e -> e.getFirstName().equals(parameterMap.get("First Name")))
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(customerList);

    }

    public int createCustomer(CustomerInfo customerDTO, Connection conn) throws SQLException, IllegalAccessException {
        Customer newCustomer = new Customer(conn, UUID.randomUUID(), customerDTO.getFirstname(),
                customerDTO.getLastname(), customerDTO.getCustomernum());
        CustomerController.createCustomer(newCustomer);
        return 201;
    }
}
