package com.revature.airline.controller;

import org.junit.Test;
import com.revature.airline.repos.Customer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @Test
    public void test_CreateCustomer() throws SQLException, IllegalAccessException {
        Connection mockConn = mock(Connection.class);
        Customer mockCustomer = mock(Customer.class);
        CustomerController mockController = mock(CustomerController.class);

        //when(mockCustomer.save()).thenReturn()



    }
}
