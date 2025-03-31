package com.hexaware.entity;

public abstract class Event {
    protected String eventName;
    protected String eventDate;
    protected String eventTime;
    protected int totalSeats;
    protected int availableSeats;
    protected double ticketPrice;
    protected String venueName;

    public Event(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String venueName) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.venueName = venueName;
    }

    public abstract void displayEventDetails();

    public boolean bookTickets(int numTickets) {
        if (numTickets <= availableSeats) {
            availableSeats -= numTickets;
            return true;
        }
        return false;
    }

    public void cancelTickets(int numTickets) {
        availableSeats += numTickets;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
