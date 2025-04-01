package com.hexaware.entity;

import java.util.ArrayList;

public class EventServiceProviderImpl implements IEventServiceProvider{
    protected ArrayList<Event> events = new ArrayList<>();

    @Override
    public Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        Event event = switch (eventType.toLowerCase()) {
            case "movie" -> new Movie(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue);
            case "concert" -> new Concert(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue);
            case "sport" -> new Sport(eventName, eventDate, eventTime, totalSeats, ticketPrice, venue);
            default -> null;
        };
        if (event != null) events.add(event);
        return event;
    }

    @Override
    public ArrayList<Event> getEventDetails() {
        return events;
    }

    @Override
    public int getAvailableNoOfTickets() {
        return events.stream().mapToInt(Event::getAvailableSeats).sum();
    }
}
