package com.revature.airline.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//arrange
//act
//assert

//@BeforeClass //before all tests in class
//@AfterClass //after all tests in class
//@Before //before each test
//@After    //after each test

public class PingServletTest extends Mockito {

    @Test
    public void testPingServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

    }
}
