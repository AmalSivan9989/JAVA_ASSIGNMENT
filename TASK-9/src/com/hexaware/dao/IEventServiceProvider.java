package com.hexaware.dao;

import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;

import java.util.List;

public interface IEventServiceProvider {
    Event createEvent(String eventName, String date, String time, int totalSeats, double ticketPrice, String eventType, Venue venue);
    List<Event> getEventDetails();
    int getAvailableNoOfTickets(String eventName);
}
