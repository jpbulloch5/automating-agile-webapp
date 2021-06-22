package com.revature.airline.dtos;

/**
 * These DTOs are super simple java objects used by the service layer. This data goes to an entity/repository
 * object to be persisted in the database, or is marshalled as a JSON string by jackson to be sent over HTTP.
 */
public class CustomerInfo {
    private String firstname;
    private String lastname;
    private int customernum;

    public CustomerInfo() {
        super();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getCustomernum() {
        return customernum;
    }

    public void setCustomernum(int customernum) {
        this.customernum = customernum;
    }
}
