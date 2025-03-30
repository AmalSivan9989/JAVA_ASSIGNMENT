package com.assignment.client;

import com.assignment.entity.Customer;

import java.util.Scanner;

public class CustomerClient {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Customer cu=new Customer();
        System.out.print("enter customer name:");
        String a=sc.next();
        cu.setCustomer_name(a);
        System.out.print("enter customer email id:");
        String b=sc.next();
        cu.setEmail(b);
        System.out.print("enter phone number:");
        long c=sc.nextLong();
        cu.setPhone_number(c);
        cu=new Customer(a,b,c);

        System.out.println("Customer Details:");
        cu.display_customer_details();

    }
}
