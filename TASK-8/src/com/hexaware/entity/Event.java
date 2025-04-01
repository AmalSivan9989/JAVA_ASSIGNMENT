package com.hexaware.entity;

public abstract class Event {
    protected String eventName, eventDate, eventTime, eventType;
    protected Venue venue;
    protected int totalSeats, availableSeats;
    protected double ticketPrice;

    public Event(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
        this.venue = venue;
    }

    public boolean bookTickets(int numTickets) {
        if (numTickets <= availableSeats) {
            availableSeats -= numTickets;
            return true;
        }
        return false;
    }

    public void cancelBooking(int numTickets) {
        availableSeats += numTickets;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public abstract void displayEventDetails();
}
