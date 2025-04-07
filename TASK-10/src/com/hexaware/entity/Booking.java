package com.hexaware.entity;

import java.util.List;
import java.util.Set;

public class Booking {
    private int bookingId;
    private Event event;
    private int numTickets;
    private double totalCost;
    private Set<Customer> customers;

    public Booking(int bookingId, Event event, int numTickets, double totalCost, Set<Customer> customers) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId;
    }

    @Override
    public int hashCode() {
        return bookingId;
    }
}