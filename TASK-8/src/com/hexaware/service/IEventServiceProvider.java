package com.hexaware.service;

import com.hexaware.bean.Event;
import com.hexaware.bean.Venue;

import java.util.List;

public interface IEventServiceProvider {
    Event createEvent(String eventName, String date, String time, int totalSeats, double ticketPrice, String eventType, Venue venue);
    List<Event> getEventDetails();
    int getAvailableNoOfTickets();
}