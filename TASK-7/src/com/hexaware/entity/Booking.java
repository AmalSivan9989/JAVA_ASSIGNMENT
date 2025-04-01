package com.hexaware.entity;

import java.util.ArrayList;
import java.util.Date;

public class Booking {
    private static int bookingCounter = 1;
    private int bookingId;
    private ArrayList<Customer> customers;
    private Event event;
    private int numTickets;
    private double totalCost;
    private Date bookingDate;

    public Booking(Event event, int numTickets, ArrayList<Customer> customers) {
        this.bookingId = bookingCounter++;
        this.event = event;
        this.numTickets = numTickets;
        this.customers = customers;
        this.totalCost = numTickets * event.ticketPrice;
        this.bookingDate = new Date();
    }

    public void displayBookingDetails() {
        System.out.println("Booking ID: " + bookingId + ", Event: " + event.eventName + ", Tickets: " + numTickets + ", Total Cost: " + totalCost);
    }
}
