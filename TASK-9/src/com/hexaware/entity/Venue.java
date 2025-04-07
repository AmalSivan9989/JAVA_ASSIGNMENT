package com.hexaware.entity;

public class Venue {
    private String venueName;
    private String location;

    public Venue(String venueName, String location) {
        this.venueName = venueName;
        this.location = location;
    }

    public String getVenueName() { return venueName; }
    public String getLocation() { return location; }
}