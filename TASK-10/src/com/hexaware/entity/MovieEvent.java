package com.hexaware.entity;

public class MovieEvent extends Event {
    public MovieEvent(String eventName, Venue venue, int totalSeats, double ticketPrice) {
        super(eventName, null, null, venue, totalSeats, totalSeats, ticketPrice, "Movie");
    }
}