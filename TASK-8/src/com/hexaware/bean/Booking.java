package com.hexaware.bean;

import java.util.List;

public class Booking {
    private int bookingId;
    private Event event;
    private int numTickets;
    private double totalCost;
    private List<Customer> customers;

    public Booking(int bookingId, Event event, int numTickets, double totalCost, List<Customer> customers) {
        this.bookingId = bookingId;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
        this.customers = customers;
    }

    public int getBookingId() { return bookingId; }
    public Event getEvent() { return event; }
    public int getNumTickets() { return numTickets; }

    public void printBookingDetails() {
        System.out.println("Booking ID: " + bookingId + ", Event: " + event.getEventName() + ", Tickets: " + numTickets + ", Total Cost: $" + totalCost);
    }
}