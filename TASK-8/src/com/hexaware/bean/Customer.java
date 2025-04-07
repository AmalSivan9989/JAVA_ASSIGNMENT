package com.hexaware.bean;

public class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void printCustomerDetails() {
        System.out.println("Customer: " + name + ", Email: " + email);
    }
}
