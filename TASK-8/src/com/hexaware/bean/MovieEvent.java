package com.hexaware.bean;

public class MovieEvent extends Event {
    public MovieEvent(String eventName, Venue venue, int totalSeats, double ticketPrice) {
        super(eventName, null, null, venue, totalSeats, ticketPrice, "Movie");
    }
}