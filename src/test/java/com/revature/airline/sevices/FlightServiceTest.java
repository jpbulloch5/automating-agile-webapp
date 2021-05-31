package com.revature.airline.sevices;
import com.revature.airline.controller.CustomerController;
import com.revature.airline.controller.FlightController;
import com.revature.airline.dtos.CustomerInfo;
import com.revature.airline.dtos.FlightDTO;
import com.revature.airline.repos.Customer;
import com.revature.airline.repos.Flight;
import com.revature.airline.services.CustomerService;
import com.revature.airline.services.FlightService;
import eorm.utils.Repository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.mockito.Mockito.*;

public class FlightServiceTest {
    private MockedStatic<FlightController> mockFlightController;

    @Before
    public void setUp(){
        mockFlightController = Mockito.mockStatic(FlightController.class);

    }

    @After
    public void tearDown(){
        mockFlightController.close ();
    }

    @Test
    public void test_getAllFlights() throws SQLException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Connection mockConn = mock(Connection.class);
        List<Repository> mockList = new LinkedList<Repository> ();
        Flight newFlight = new Flight(mockConn, UUID.fromString("eb6d2d8d-ab42-483a-9a79-432d8865b430"), 0, "Miami", "New York", "7:30 PM", "A30", "B20");
        mockList.add(newFlight);
        Flight anotherFlight = new Flight(mockConn, UUID.fromString("bc9ab8d9-2a14-44d4-befd-2ac7553fd4ad"), 1, "Las Vegas", "Detroit", "9:00pm", "B20", "A50");
        mockList.add(anotherFlight);
        mockFlightController.when(() -> FlightController.getAllFlights (mockConn)).thenReturn(mockList);
        FlightService sut = new FlightService();




        Assert.assertEquals("[{\"flight_id\":\"eb6d2d8d-ab42-483a-9a79-432d8865b430\",\"flightNum\":0,\"departureLocation\":\"Miami\",\"destinationLocation\":\"New York\",\"departureTime\":\"7:30 PM\",\"departureGate\":\"A30\",\"destinationGate\":\"B20\"}," +
                        "{\"flight_id\":\"bc9ab8d9-2a14-44d4-befd-2ac7553fd4ad\",\"flightNum\":1,\"departureLocation\":\"Las Vegas\",\"destinationLocation\":\"Detroit\",\"departureTime\":\"9:00pm\",\"departureGate\":\"B20\",\"destinationGate\":\"A50\"}]",
                sut.getAllFlights(mockConn));

    }

    @Test
    public void test_flightLookUp() throws SQLException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Connection mockConn = mock(Connection.class);
        List<Repository> mockList = new LinkedList<> ();

        Flight newFlight = new Flight(mockConn, UUID.fromString("eb6d2d8d-ab42-483a-9a79-432d8865b430"), 0, "Miami", "New York", "7:30 PM", "A30", "B20");
        mockList.add(newFlight);
        Flight badFlight = new Flight(mockConn, UUID.fromString("bc9ab8d9-2a14-44d4-befd-2ac7553fd4ad"), 1, "Las Vegas", "Detroit", "9:00pm", "B20", "A50");
        mockList.add(badFlight);
        mockFlightController.when(() -> FlightController.lookUpFlights (mockConn)).thenReturn(mockList);
        FlightService sut = new FlightService();
        Map<String, String> mockParams = new HashMap<>();
        mockParams.put("departureLocation", "Miami");
        mockParams.put("DestinationLocation", "New York");

        Assert.assertEquals("[{\"flight_id\":\"eb6d2d8d-ab42-483a-9a79-432d8865b430\",\"flightNum\":0,\"departureLocation\":\"Miami\",\"destinationLocation\":\"New York\",\"departureTime\":\"7:30 PM\",\"departureGate\":\"A30\",\"destinationGate\":\"B20\"}]",
                sut.flightLookup (mockParams, mockConn));
    }

    @Test
    public void test_createflight() throws SQLException, IllegalAccessException {
        Connection mockConn = mock(Connection.class);
        FlightDTO mockDTO = mock(FlightDTO.class);
        when(mockDTO.getFlightNum ()).thenReturn(1);
        when(mockDTO.getDepartureLocation ()).thenReturn("Mockville");
        when(mockDTO.getDestinationLocation ()).thenReturn("MockPlace");
        when(mockDTO.getDepartureTime ()).thenReturn("AnyTime");
        when(mockDTO.getDepartureGate ()).thenReturn("AnyGate");
        when(mockDTO.getDestinationGate()).thenReturn("AnyOtherGate");


        Flight mockFlight = mock(Flight.class);
        FlightService sut = new FlightService();


        Assert.assertEquals(201, sut.createFlight(mockDTO, mockConn));
    }



}
