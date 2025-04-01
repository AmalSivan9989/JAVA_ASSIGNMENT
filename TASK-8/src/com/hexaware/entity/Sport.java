package com.hexaware.entity;

public class Sport extends Event{
    private String sportName;
    private String teamsName;




    public Sport(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, Venue venue) {
        super(eventName, eventDate, eventTime, totalSeats, ticketPrice, "Sport", venue);
    }

    @Override
    public void displayEventDetails() {
        System.out.println("Sport: " + eventName + " (" + sportName + " - " + teamsName + ") at " + venue + " on " + eventDate + " " + eventTime);
    }

}

