package com.assignment.entity;

public class Venue {
    private String venue_name;
    private String address;

    public Venue(){
    }

    public Venue(String address, String venue_name) {
        this.address = address;
        this.venue_name = venue_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public void display_venue_details(){
        System.out.println("Venue name: "+getVenue_name());
        System.out.println("address: "+getAddress());
    }
}
