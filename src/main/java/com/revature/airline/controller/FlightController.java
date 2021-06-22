package com.revature.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.airline.dtos.FlightDTO;
import com.revature.airline.repos.Flight;
import com.revature.airline.repos.Ticket;
import eorm.utils.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * These functions are called by the service layer and invoke the entity/repository CRUD functions.
 */
public class FlightController {

    public static List<Repository> getAllFlights(Connection conn)
            throws IOException, SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        return Flight.query(conn, Flight.class);
    }

    public static List<Repository> lookUpFlights(Connection conn)
            throws IOException, SQLException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        return Flight.query(conn, Flight.class);
    }


    public static void createFlight(Flight flight) throws SQLException, IllegalAccessException {
        flight.save();
    }
}
