package com.hexaware.dao;

import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;

import java.util.List;
import java.util.Set;

public interface IEventServiceProvider {
    Event createEvent(String eventName, String date, String time, int totalSeats, double ticketPrice, String eventType, Venue venue);
    Set<Event> getEventDetails();
    int getAvailableNoOfTickets();
}