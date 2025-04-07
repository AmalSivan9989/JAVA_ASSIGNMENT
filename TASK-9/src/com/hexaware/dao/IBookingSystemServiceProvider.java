package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

import java.util.List;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(int numTickets, double ticketPrice);
    Booking bookTickets(String eventName, int numTickets, List<Customer> customers) throws EventNotFoundException;
    boolean cancelBooking(int bookingId) throws InvalidBookingIDException;
    Booking getBookingDetails(int bookingId) throws InvalidBookingIDException;
    public abstract Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue);
    public List<Event> getEventDetails();
    public abstract int getAvailableSeats(String eventName) throws EventNotFoundException;
}
