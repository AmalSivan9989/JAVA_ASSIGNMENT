package com.hexaware.service;

import com.hexaware.bean.Event;
import com.hexaware.bean.Venue;

import java.util.ArrayList;
import java.util.List;

public class EventServiceProviderImpl implements IEventServiceProvider {
    private List<Event> events = new ArrayList<>();

    @Override
    public Event createEvent(String eventName, String date, String time, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        Event event = new Event(eventName, date, time, venue, totalSeats, ticketPrice, eventType);
        events.add(event);
        return event;
    }

    @Override
    public List<Event> getEventDetails() {
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