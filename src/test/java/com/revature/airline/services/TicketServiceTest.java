package com.revature.airline.services;

import com.revature.airline.controller.TicketController;
import com.revature.airline.dtos.TicketPurchaseDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketServiceTest {
    private MockedStatic<TicketController> mockTicketController;

    @Before
    public void setUp() {
        mockTicketController = Mockito.mockStatic(TicketController.class);
    }

    @After
    public void tearDown() {
        mockTicketController.close();
        mockTicketController = null;
    }

    @Test
    public void test_purchaseTickets() throws SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        TicketService sut = new TicketService();
        Connection mockConn = mock(Connection.class);
        TicketPurchaseDTO mockDTO = mock(TicketPurchaseDTO.class);
        mockTicketController.when(() -> TicketController.verifySpace(mockConn, "eb6d2d8d-ab42-483a-9a79-432d8865b430")).thenReturn(0);
        when(mockDTO.getCustomerID()).thenReturn(UUID.randomUUID().toString());
        when(mockDTO.getFlightID()).thenReturn(UUID.randomUUID().toString());

        Assert.assertEquals(201, sut.purchaseTicket(mockDTO, mockConn));

    }

    @Test
    public void test_purchaseSoldOutTickets() throws SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        TicketService sut = new TicketService();
        String mockID = "2427d961-c9d3-4717-b28c-ec684899812a";
        Connection mockConn = mock(Connection.class);
        TicketPurchaseDTO mockDTO = mock(TicketPurchaseDTO.class);
        mockTicketController.when(() -> TicketController.verifySpace(mockConn, mockID)).thenReturn(-1);
        when(mockDTO.getCustomerID()).thenReturn(mockID);
        when(mockDTO.getFlightID()).thenReturn(mockID);

        Assert.assertEquals(406, sut.purchaseTicket(mockDTO, mockConn));

    }

}
