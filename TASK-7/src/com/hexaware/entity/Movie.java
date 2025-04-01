package com.hexaware.entity;

class Movie extends Event {
    public Movie(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, Venue venue) {
        super(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue);
    }
    @Override
    public void displayEventDetails() {
        System.out.println("Movie: " + eventName + " at " + venue + " on " + eventDate + " " + eventTime);
    }
}


