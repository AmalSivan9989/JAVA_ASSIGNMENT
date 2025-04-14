package com.hexaware.dao;

import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;

import java.util.*;

public class EventServiceProviderImpl implements IEventServiceProvider {
    private Map<String, Event> eventMap;

    public EventServiceProviderImpl() {
        this.eventMap = new HashMap<>();
    }

    @Override
    public Event createEvent(String eventName, String date, String time, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        Event event = new Event(eventName, date, time, venue, totalSeats, totalSeats, ticketPrice, eventType);
        eventMap.put(eventName, event);
        return event;
    }




    @Override
    public Map<String, Event> getEventDetails() {
        return eventMap;
    }

    @Override
    public int getAvailableNoOfTickets() {
        int totalAvailable = 0;
        for (Event event : eventMap.values()) {
            totalAvailable += event.getAvailableSeats();
        }
        return totalAvailable;
    }
    public Event getEventByName(String eventName) {
        return eventMap.get(eventName.toLowerCase());
    }
}