package com.revature.airline.servlets;

import com.revature.airline.controller.TicketController;
import com.revature.airline.services.CustomerService;
import com.revature.airline.services.FlightService;
import com.revature.airline.services.TicketService;
import com.revature.airline.utils.DatabaseInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import static org.mockito.Mockito.*;

public class DispatcherServletTest {
    DispatcherServlet mockDispatcher;
    Connection mockConn;


    MockedStatic<DatabaseInitializer> mockDatabaseInitializer;

    @Before
    public void setUp() {
        mockDatabaseInitializer = Mockito.mockStatic(DatabaseInitializer.class);
        mockDispatcher = new DispatcherServlet();
    }

    @After
    public void tearDown() {
        mockDatabaseInitializer.close();
        mockDispatcher = null;
    }

    @Test
    public void test_dispatchToFlightsGet() throws SQLException, IOException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException, ServletException {
//                    String allFlights = flightService.getAllFlights(conn);
//                    resp.setStatus(200);
//                    resp.getWriter().println(allFlights);
        //      String target = parseURI(req);
        //        Map<String, String> parameterMap = new HashMap<>();
        //        Enumeration<String> parameterNames = req.getParameterNames();
        //        while(parameterNames.hasMoreElements()) {
        //            String element = parameterNames.nextElement();
        //            parameterMap.put(element, req.getParameter(element));
        //        }
        Connection mockConn = mock(Connection.class);
        HttpServletRequest mockReq = mock(HttpServletRequest.class);
        HttpServletResponse mockResp = mock(HttpServletResponse.class);
        FlightService mockflightService = mock(FlightService.class);
        TicketService mockTicketService = mock(TicketService.class);
        CustomerService mockCustomerService = mock(CustomerService.class);


        Vector<String> v = new Vector<>();
        v.add("Param1");
        v.add("Param2");
        when(mockReq.getRequestURI()).thenReturn("/test/webapp/flights");
        when(mockReq.getParameterNames()).thenReturn(v.elements());
        when(mockReq.getParameter("Param1")).thenReturn("Value1");
        when(mockReq.getParameter("Param2")).thenReturn("Value2");
        mockDatabaseInitializer.when(DatabaseInitializer::getconnection).thenReturn(mockConn);
        when(mockflightService.getAllFlights(mockConn)).thenReturn("testJSON");
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(mockResp.getWriter()).thenReturn(mockPrintWriter);


        mockDispatcher.testInit(mockConn, mockflightService, mockTicketService, mockCustomerService);
        mockDispatcher.doGet(mockReq, mockResp);

        verify(mockflightService, times(1)).getAllFlights(mockConn);


    }

    @Test
    public void test_dispatchToCustomerGet() {

    }

    @Test
    public void test_dispatchToFlightLookupGet() {

    }

    @Test
    public void test_dispatchToPurchasePost() {

    }

    @Test
    public void test_dispatchToCreateCustomerPost() {

    }

    @Test
    public void test_dispatchToCreateFlightPost() {

    }

}
