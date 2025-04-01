package com.hexaware.entity;

import java.util.ArrayList;

public class TicketBookingSystem {
    private ArrayList<Event> events = new ArrayList<>();

    public Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue, String sportName, String teamsName) {
        Event event = null;
        switch (eventType.toLowerCase()) {
            case "movie":
                event = new Movie(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue);
                break;
            case "concert":
                event = new Concert(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue);
                break;
            case "sport":
                event = new Sport(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue, sportName, teamsName);
                break;
            default:
                System.out.println("Invalid event type!");
                return null;
        }
        events.add(event);
        return event;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
