package com.hexaware.entity;

public class Venue {
    private String venueName;
    private String address;
    private int venueId;

    public Venue(int venueId) {
        this.venueId = venueId;
    }

    public Venue(String venueName, String address) {
        this.venueName = venueName;
        this.address = address;
    }
    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "address='" + address + '\'' +
                ", venueName='" + venueName + '\'' +
                '}';
    }
}
