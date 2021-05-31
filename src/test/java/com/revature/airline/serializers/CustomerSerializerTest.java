package com.revature.airline.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.repos.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class CustomerSerializerTest {

    @Test
    public void test_customerSerializationToJSON() throws JsonProcessingException {
        Connection mockConn = mock(Connection.class);
        UUID mockUUID = UUID.fromString("2427d961-c9d3-4717-b28c-ec684899812a");
        String mockFirstName = "firstName";
        String mockLastName = "lastName";
        int mockCustNum = 4;
        Customer customer = new Customer(mockConn, mockUUID,
                mockFirstName, mockLastName, mockCustNum);
        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals("{\"customer_id\":\"" + mockUUID + "\"," +
                        "\"firstName\":\"" + mockFirstName + "\"," +
                        "\"lastName\":\"" + mockLastName + "\"," +
                        "\"customerNum\":" + mockCustNum + "}",
                mapper.writeValueAsString(customer));
    }

}
