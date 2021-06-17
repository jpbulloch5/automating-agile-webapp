package com.revature.airline.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.airline.repos.Customer;

import java.io.IOException;

/**
 * These serializers exist because jackson doesn't like the fields inherited by entities from the
 * repository class in the ORM. There are annotations that can exclude fields or tell jackson to ignore them.
 * It probably isn't necessary to always include custom serializers on entities.
 */
public class CustomerSerializer extends StdSerializer<Customer> {
    public CustomerSerializer() {
        this(null);
    }

    protected CustomerSerializer(Class<Customer> t) {
        super(t);
    }
    @Override
    public void serialize(Customer customer, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("customer_id", customer.getCustomer_id().toString());
        gen.writeStringField("firstName", customer.getFirstName());
        gen.writeStringField("lastName", customer.getLastName());
        gen.writeNumberField("customerNum", customer.getCustomerNum());
        gen.writeEndObject();
    }
}
