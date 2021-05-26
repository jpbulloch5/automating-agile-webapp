package com.revature.airline.pojo;


public class Customer {
    private String firstName;

    public Customer(String firstName, String lastName, int customerNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNum = customerNum;
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

    private String lastName;
    private int customerNum;
}
