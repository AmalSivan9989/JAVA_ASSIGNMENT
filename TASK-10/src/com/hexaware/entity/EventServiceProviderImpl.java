package com.hexaware.entity;

import com.hexaware.dao.IEventServiceProvider;

import java.util.*;

public class EventServiceProviderImpl implements IEventServiceProvider {
    private Set<Event> events = new HashSet<>();

    public EventServiceProviderImpl() {
        this.events = new TreeSet<>(Comparator.comparing(Event::getEventName)
                .thenComparing(e -> e.getVenue().getLocation()));
    }

    @Override
    public Event createEvent(String eventName, String date, String time, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        Event event = new Event(eventName, date, time, venue, totalSeats, totalSeats, ticketPrice, eventType);
        events.add(event);
        return event;
    }

    @Override
    public Set<Event> getEventDetails() {
        return events;
    }

    @Override
    public int getAvailableNoOfTickets() {
        int totalAvailable = 0;
        for (Event event : events) {
            totalAvailable += event.getAvailableSeats();
        }
        return totalAvailable;
    }
}
