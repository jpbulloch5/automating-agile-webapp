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

/**
 * This service layer class handles all requests involving the customer resource.
 */
public class CustomerService {

    /**
     * getCustomers returns all customers in the database, and then sorts and filters them as a stream
     * in order to return those matching the provided first and last names. In a perfect world we wouldn't
     * return the entire table to be sorted by the app, but we decided this was a simpler solution
     * than designing the ORM to create more complex queries.
     * @param parameterMap - a map of parameters pulled from the HTTP request web form parameters.
     * @param conn - a connection object connected to the datasource.
     * @return - JSON string representing a list of customer entities
     * @throws SQLException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
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

    /**
     * This method marshals a customer repository/entity from a DTO and sends it to the controller to be persisted.
     * @param customerDTO - a data object representing the customer resource.
     * @param conn - a connection object connected to the datasource.
     * @return - successful status code 201.
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public int createCustomer(CustomerInfo customerDTO, Connection conn) throws SQLException, IllegalAccessException {
        Customer newCustomer = new Customer(conn, UUID.randomUUID(), customerDTO.getFirstname(),
                customerDTO.getLastname(), customerDTO.getCustomernum());
        CustomerController.createCustomer(newCustomer);
        return 201;
    }
}
