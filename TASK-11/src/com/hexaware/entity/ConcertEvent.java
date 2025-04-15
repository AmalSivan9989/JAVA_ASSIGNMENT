package com.hexaware.entity;

public class ConcertEvent extends Event {
    public ConcertEvent(String eventName, String eventDate, String eventTime, Venue venue, int totalSeats, double ticketPrice) {
        super(eventName, eventDate, eventTime, venue, totalSeats, totalSeats, ticketPrice, "Concert");
    }
}