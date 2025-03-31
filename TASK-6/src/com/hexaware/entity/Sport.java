package com.hexaware.entity;

public class Sport extends Event{
    private String sportName;
    private String teamsName;

    public Sport(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String venueName, String sportName, String teamsName) {
        super(eventName, eventDate, eventTime, totalSeats, ticketPrice, venueName);
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getTeamsName() {
        return teamsName;
    }

    public void setTeamsName(String teamsName) {
        this.teamsName = teamsName;
    }

    @Override
    public void displayEventDetails() {
        System.out.println("Sport Event: " + eventName + " (" + sportName + " - " + teamsName + ") at " + venueName + " on " + eventDate + " " + eventTime);
    }

}

