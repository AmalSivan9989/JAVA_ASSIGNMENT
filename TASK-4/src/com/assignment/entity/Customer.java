package com.assignment.entity;

public class Customer {
    private String customer_name;
    private String email;
    private long phone_number;
   public Customer(){

    }

    public Customer(String customer_name, String email, long  phone_number) {
        this.customer_name = customer_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
    public void display_customer_details(){
        System.out.println("customer name:"+getCustomer_name());
        System.out.println("email:"+getEmail());
        System.out.println("phone number:"+getPhone_number());
    }
}
