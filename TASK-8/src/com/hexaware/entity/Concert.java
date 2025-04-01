package com.hexaware.entity;

public class Concert extends Event {
    public Concert(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, Venue venue) {
        super(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue);
    }
    @Override
    public void displayEventDetails() {
        System.out.println("Concert: " + eventName + " at " + venue + " on " + eventDate + " " + eventTime);
    }
}