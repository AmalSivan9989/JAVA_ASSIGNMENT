package com.hexaware.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Booking {
    private static int idCounter = 1000;
    private int bookingId;
    private String eventName;
    private int numberOfTickets;
    private Map<Integer, Customer> customers; // Changed from List to Map

    public Booking(String eventName, int numberOfTickets, Map<Integer, Customer> customers) {
        this.bookingId = ++idCounter;
        this.eventName = eventName;
        this.numberOfTickets = numberOfTickets;
        this.customers = customers;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getEventName() {
        return eventName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                ", Event: " + eventName +
                ", Tickets: " + numberOfTickets +
                ", Customers: " + customers.values();
    }
}