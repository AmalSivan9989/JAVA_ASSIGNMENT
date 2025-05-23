package com.hexaware.entity;

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

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                "\nEvent: " + event.getEventName() +
                "\nTickets: " + numTickets +
                "\nTotal Cost: $" + totalCost;
    }
}