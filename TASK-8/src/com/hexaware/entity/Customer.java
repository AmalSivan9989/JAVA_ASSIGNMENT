package com.hexaware.entity;

public class Customer {
    private String customerName;
    private String email;
    private String phoneNumber;

    public Customer(String customerName, String email, String phoneNumber) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void displayCustomerDetails() {
        System.out.println("Customer: " + customerName + ", Email: " + email + ", Phone: " + phoneNumber);
    }
}
