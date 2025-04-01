package com.hexaware.entity;

import com.hexaware.entity.Event;

import java.util.ArrayList;
import java.util.Date;

public class Booking {
    private static int bookingCounter = 1;
    private int bookingId;
    private Customer[] customers;
    Event event;
    int numTickets;
    private double totalCost;
    private Date bookingDate;

    public Booking(Customer[] customers, Event event, int numTickets) {
        this.bookingId = bookingCounter++;
        this.customers = customers;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = numTickets * event.getTicketPrice();
        this.bookingDate = new Date();
    }

    public void displayBookingDetails() {
        System.out.println("Booking ID: " + bookingId + ", Event: " + event.eventName + ", Tickets: " + numTickets + ", Cost: $" + totalCost);
    }

    public int getBookingId() {
        return bookingId;
    }
}
