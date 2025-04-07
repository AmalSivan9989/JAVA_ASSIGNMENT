package com.hexaware.bean;

public class SportsEvent extends Event {
    public SportsEvent(String eventName, Venue venue, int totalSeats, double ticketPrice) {
        super(eventName, null, null, venue, totalSeats, ticketPrice, "Sports");
    }
}
