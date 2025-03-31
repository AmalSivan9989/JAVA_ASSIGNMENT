package com.hexaware.entity;

public class Concert extends Event {
    private String artist;
    private String type;

    public Concert(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String venueName, String artist, String type) {
        super(eventName, eventDate, eventTime, totalSeats, ticketPrice, venueName);
        this.artist = artist;
        this.type = type;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public void displayEventDetails() {
        System.out.println("Concert Event: " + eventName + " by " + artist + " (Type: " + type + ") at " + venueName + " on " + eventDate + " " + eventTime);
    }
}