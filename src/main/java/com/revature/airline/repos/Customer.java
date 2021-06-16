package com.revature.airline.repos;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.revature.airline.serializers.CustomerSerializer;
import eorm.annotations.Column;
import eorm.annotations.Table;
import eorm.enums.SQLType;
import eorm.utils.Repository;

import java.sql.Connection;
import java.util.UUID;

/**
 * Inherits methods from the Orm's Repository class.
 * Implements the functionality of a plain old java object while also leveraging JDBC logic implemented in the ORM.
 */

@JsonSerialize(using = CustomerSerializer.class)
@Table(tableName = "customer")
public class Customer extends Repository {

    @Column(type = SQLType.UUID, primaryKey = true)
    private UUID customer_id;

    @Column(type = SQLType.VARCHAR)
    private String firstName;

    @Column(type = SQLType.VARCHAR)
    private String lastName;

    @Column(type = SQLType.INT)
    private int customerNum;

    public Customer(Connection conn) {
        super(conn);
    }

    public Customer(Connection conn, UUID id, String firstName, String lastName, int customerNum) {
        super(conn);
        this.customer_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNum = customerNum;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\"customer_id\":\"" + customer_id + "\"" +
                ",\n\t\"firstName\":" + firstName +
                ",\n\t\"lastName\":\"" + lastName + "\"" +
                ",\n\t\"customerNum\":\"" + customerNum + "\"" +
                "\n}\n";
    }

}
