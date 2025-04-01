package com.hexaware.entity;

public abstract class Event {
    protected String eventName;
    protected String eventDate;
    protected String eventTime;
    protected Venue venue;
    protected int totalSeats;
    protected int availableSeats;
    protected double ticketPrice;

    public Event(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, Venue venue) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.venue = venue;
    }

    public abstract void displayEventDetails();

    public double calculateTotalRevenue() {
        return (totalSeats - availableSeats) * ticketPrice;
    }

    public int getBookedNoOfTickets() {
        return totalSeats - availableSeats;
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
    }

