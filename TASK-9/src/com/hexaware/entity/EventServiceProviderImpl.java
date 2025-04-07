package com.hexaware.entity;

import com.hexaware.dao.IEventServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class EventServiceProviderImpl implements IEventServiceProvider {
    private List<Event> events;

    public EventServiceProviderImpl() {
        this.events = new ArrayList<>();
    }

    @Override
    public Event createEvent(String eventName, String date, String time, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        Event event = new Event(eventName, date, time, venue, totalSeats, totalSeats, ticketPrice, eventType);
        events.add(event);
        return event;
    }

    @Override
    public List<Event> getEventDetails() {
        return events;
    }

    @Override
    public int getAvailableNoOfTickets(String eventName) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventName)) {
                return event.getAvailableSeats();
            }
        }
        return 0;
    }
}