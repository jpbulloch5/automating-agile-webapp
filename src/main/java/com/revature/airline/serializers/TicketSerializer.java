package com.revature.airline.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.airline.repos.Ticket;

import java.io.IOException;

public class TicketSerializer extends StdSerializer<Ticket> {
    public TicketSerializer() {
        this(null);
    }

    protected TicketSerializer(Class<Ticket> t) {
        super(t);
    }

    @Override
    public void serialize(Ticket ticket, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("ticket_id", ticket.getTicket_id().toString());
        gen.writeStringField("customer_id", ticket.getCustomer_id().toString());
        gen.writeStringField("flight_id", ticket.getFlight_id().toString());
        gen.writeNumberField("seat", ticket.getSeat());
        gen.writeEndObject();
    }

}
