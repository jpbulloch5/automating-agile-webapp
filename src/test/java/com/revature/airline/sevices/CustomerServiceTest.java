package com.revature.airline.sevices;

import com.revature.airline.controller.CustomerController;
import com.revature.airline.dtos.CustomerInfo;
import com.revature.airline.repos.Customer;
import com.revature.airline.services.CustomerService;
import eorm.utils.Repository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


//arrange
//act
//assert

//@BeforeClass //before all tests in class
//@AfterClass //after all tests in class
//@Before //before each test
//@After    //after each test
public class CustomerServiceTest {
    private MockedStatic<CustomerController> mockCustomerController;

    @Before
    public void setUp() {
        mockCustomerController = Mockito.mockStatic(CustomerController.class);

    }

    @After
    public void tearDown() {
        mockCustomerController.close();
        mockCustomerController = null;
    }


    @Test
    public void test_getCustomers_firstNameLastName() throws SQLException, IOException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {

        Connection mockConn = mock(Connection.class);

        List<Repository> mockList = new LinkedList<Repository>();
        Customer newCustomer = new Customer(mockConn, UUID.fromString("eb6d2d8d-ab42-483a-9a79-432d8865b430"), "testFirstName", "testLastName", 1);
        mockList.add(newCustomer);
        Customer badCustomer = new Customer(mockConn, UUID.fromString("bc9ab8d9-2a14-44d4-befd-2ac7553fd4ad"), "badFirstName", "badLastName", 2);
        mockList.add(badCustomer);
        mockCustomerController.when(() -> CustomerController.getCustomers(mockConn)).thenReturn(mockList);
        CustomerService sut = new CustomerService();
        Map<String, String> mockParams = new HashMap<>();
        mockParams.put("First Name", "testFirstName");
        mockParams.put("Last Name", "testLastName");


        Assert.assertEquals("[{\"customer_id\":\"eb6d2d8d-ab42-483a-9a79-432d8865b430\",\"firstName\":" +
                "\"testFirstName\",\"lastName\":\"testLastName\",\"customerNum\":1}]",
                sut.getCustomers(mockParams, mockConn));

    }

    @Test
    public void test_createCustomer() throws SQLException, IllegalAccessException {
        Connection mockConn = mock(Connection.class);
        CustomerInfo mockDTO = mock(CustomerInfo.class);
        when(mockDTO.getFirstname()).thenReturn("testFirstName");
        when(mockDTO.getLastname()).thenReturn("testLastName");
        when(mockDTO.getCustomernum()).thenReturn(1);
        Customer mockCustomer = mock(Customer.class);
        //MockedStatic<CustomerController> mockCustomerController = Mockito.mockStatic(CustomerController.class);
        //mockCustomerController.when(() -> CustomerController.createCustomer(mockCustomer)).thenReturn(null);
        //NO STUBBING VOID METHODS
        CustomerService sut = new CustomerService();


        Assert.assertEquals(201, sut.createCustomer(mockDTO, mockConn));
    }
}
