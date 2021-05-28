package com.revature.airline.repos;

import eorm.annotations.Column;
import eorm.annotations.ForeignKey;
import eorm.annotations.Table;
import eorm.enums.SQLType;
import eorm.utils.Repository;

import java.sql.Connection;
import java.util.UUID;

@Table(tableName = "ticket")
public class Ticket extends Repository {

    @Column(type = SQLType.UUID, primaryKey = true)
    private UUID ticket_id;

    @Column(type = SQLType.UUID)
    @ForeignKey(referencedTable = "customer")
    private UUID customer_id;

    @Column(type = SQLType.UUID)
    @ForeignKey(referencedTable = "flight")
    private UUID flight_id;

    @Column(type = SQLType.VARCHAR)
    private String seat;

    public Ticket(Connection conn) {
        super(conn);
    }

    public Ticket(Connection conn, UUID id, UUID customer_id, UUID flight_id, String seat) {
        super(conn);
        this.ticket_id = id;
        this.customer_id = customer_id;
        this.flight_id = flight_id;
        this.seat = seat;
    }

    public UUID getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(UUID ticket_id) {
        this.ticket_id = ticket_id;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public UUID getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(UUID flight_id) {
        this.flight_id = flight_id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
