package com.hexaware.bean;

public class Venue {
    private String name;
    private String location;
    private int capacity;

    public Venue(String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }

    public void printVenueDetails() {
        System.out.println("Venue: " + name + ", Location: " + location + ", Capacity: " + capacity);
    }
}