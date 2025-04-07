package com.hexaware.bean;

public class ConcertEvent extends Event {
    public ConcertEvent(String eventName, Venue venue, int totalSeats, double ticketPrice) {
        super(eventName, null, null, venue, totalSeats, ticketPrice, "Concert");
    }
}
