package com.hexaware.entity;

import java.util.ArrayList;

public abstract  class BookingSystem {
    protected ArrayList<Event> events = new ArrayList<>();
    public ArrayList<Event> getEvents() {
        return events;
    }

    public abstract void display_event_details(Event event);

    public abstract Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, String venueName, String additional1, String additional2);

    public abstract void cancelTickets(Event event, int numTickets);




    public abstract double bookTickets(Event event, int numTickets);



}
