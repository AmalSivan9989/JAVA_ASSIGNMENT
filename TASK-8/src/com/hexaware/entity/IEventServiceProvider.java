package com.hexaware.entity;

import java.util.ArrayList;

public interface IEventServiceProvider {
    Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue);
    ArrayList<Event> getEventDetails();
    int getAvailableNoOfTickets();
}
