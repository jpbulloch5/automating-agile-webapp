package com.revature.airline.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.airline.repos.Flight;

import java.io.IOException;

public class FlightSerializer extends StdSerializer<Flight> {
    public FlightSerializer() {
        this(null);
    }

    protected FlightSerializer(Class<Flight> t) {
        super(t);
    }

    @Override
    public void serialize(Flight flight, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("flight_id", flight.getFlight_id().toString());
        gen.writeNumberField("flightNum", flight.getFlightNum());
        gen.writeStringField("departureLocation", flight.getDepartureLocation());
        gen.writeStringField("destinationLocation", flight.getDestinationLocation());
        gen.writeStringField("departureTime", flight.getDepartureTime());
        gen.writeStringField("departureGate", flight.getDepartureGate());
        gen.writeStringField("destinationGate", flight.getDestinationGate());
        gen.writeEndObject();
    }
}
