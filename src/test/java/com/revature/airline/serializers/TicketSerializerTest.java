package com.revature.airline.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class TicketSerializerTest {

    //Ticket(Connection conn, UUID id, UUID customer_id, UUID flight_id, int seat) {
    @Test
    public void test_ticketSerializationToJSON() throws JsonProcessingException {
        Connection mockConn = mock(Connection.class);
        UUID mockID = UUID.fromString("e1cbdf8a-65b9-4753-8ac6-36f18004283c");
        UUID mockCustomerID = UUID.fromString("2427d961-c9d3-4717-b28c-ec684899812a");
        UUID mockFlightID = UUID.fromString("e068aa62-2b14-4f61-967d-425665b40120");
        int mockNum = 10;

        Ticket ticket = new Ticket(mockConn, mockID, mockCustomerID, mockFlightID, mockNum);
        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(
                "{\"ticket_id\":\"" + mockID + "\"," +
                        "\"customer_id\":\"" + mockCustomerID + "\"," +
                        "\"flight_id\":\"" + mockFlightID + "\"," +
                        "\"seat\":" + mockNum + "}",
                mapper.writeValueAsString(ticket));
    }
}
