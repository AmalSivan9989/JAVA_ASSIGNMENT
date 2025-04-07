package com.hexaware.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String eventName;
    private String eventDate;
    private String eventTime;
    private Venue venue;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private String eventType;

    public Event(String eventName, String eventDate, String eventTime, Venue venue, int totalSeats, double ticketPrice, String eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public String getEventName() { return eventName; }
    public int getAvailableSeats() { return availableSeats; }
    public double getTicketPrice() { return ticketPrice; }
    public void setAvailableSeats(int seats) { this.availableSeats = seats; }

    public void printEventDetails() {
        System.out.println("Event: " + eventName + ", Date: " + eventDate + ", Time: " + eventTime + ", Venue: " + venue.getName() +
                ", Available Seats: " + availableSeats + ", Ticket Price: " + ticketPrice + ", Type: " + eventType);
    }
}