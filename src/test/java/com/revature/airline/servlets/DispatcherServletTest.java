package com.revature.airline.servlets;

import com.revature.airline.controller.FlightController;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class DispatcherServletTest {

//    @Test
//    public void test_DoGetFlightsDispatch() throws SQLException, IOException, InvocationTargetException,
//            InstantiationException, IllegalAccessException, NoSuchMethodException {
//        //test if the dispatcher method datadispatch is called
//        DispatcherServlet mockDispatcherServlet = mock(DispatcherServlet.class);
//        FlightController mockFlightController = mock(FlightController.class);
//        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
//        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
//        Connection mockConn = mock(Connection.class);
//
//
//        new FlightsServlet().doPost(mockRequest, mockResponse);
//
//        verify(mockDispatcherServlet, times(1)).dataDispatch(mockRequest, mockResponse, "/flights");
//
//        //when(mockFlightController.getAllFlights(mockRequest, mockResponse, mockConn)).then(mockResponse.setStatus(200))
//
//
//
//    }
}
