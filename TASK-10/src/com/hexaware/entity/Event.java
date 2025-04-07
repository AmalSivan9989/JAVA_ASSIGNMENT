package com.hexaware.entity;

import java.util.Comparator;

public class Event {
    private String eventName;
    private String eventDate;
    private String eventTime;
    private Venue venue;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private String eventType;

    public Event(String eventName, String eventDate, String eventTime, Venue venue, int totalSeats, int availableSeats, double ticketPrice, String eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public String getEventName() { return eventName; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public double getTicketPrice() { return ticketPrice; }
    public Venue getVenue() {
        return venue;
    }
    public void printEventDetails() {
        System.out.println("Event: " + eventName + ", Date: " + eventDate + ", Time: " + eventTime + ", Venue: " + venue.getVenueName() +
                ", Available Seats: " + availableSeats + ", Ticket Price: " + ticketPrice + ", Type: " + eventType);
    }
    public class EventComparator implements Comparator<Event> {
        @Override
        public int compare(Event e1, Event e2) {
            int nameCompare = e1.getEventName().compareToIgnoreCase(e2.getEventName());
            if (nameCompare != 0) {
                return nameCompare;
            }
            return e1.getVenue().getLocation().compareToIgnoreCase(e2.getVenue().getLocation());
        }
    }
}