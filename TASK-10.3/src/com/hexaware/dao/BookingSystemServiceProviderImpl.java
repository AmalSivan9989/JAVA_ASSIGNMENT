package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Venue;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

import java.util.*;

public class BookingSystemServiceProviderImpl {
    private Map<Integer, Booking> bookings = new HashMap<>();
    private EventServiceProviderImpl eventService = new EventServiceProviderImpl();

    public BookingSystemServiceProviderImpl(IEventServiceProvider eventService) {
        this.eventService = (EventServiceProviderImpl) eventService;
        this.eventService.createEvent("MusicFest", "2025-04-15", "18:00", 100, 499.99, "Concert", new Venue("Open Grounds", "Chennai", 1000));
        this.eventService.createEvent("DramaNight", "2025-04-20", "19:00", 50, 299.99, "Play", new Venue("Theatre Hall", "Bangalore", 500));
    }

    public Booking bookTickets(String eventName, int numTickets, Map<Integer, Customer> customers) throws EventNotFoundException {
        int availableSeats = eventService.getAvailableNoOfTickets();
        if (availableSeats < numTickets) {
            throw new EventNotFoundException("Event '" + eventName + "' not found or not enough seats.");
        }


        if (customers.size() != numTickets) {
            throw new IllegalArgumentException("Number of customers must match the number of tickets.");
        }

        Booking booking = new Booking(eventName, numTickets, customers);
        bookings.put(booking.getBookingId(), booking);
        return booking;
    }

    public boolean cancelBooking(int bookingId) throws InvalidBookingIDException {
        if (bookings.containsKey(bookingId)) {
            bookings.remove(bookingId);
            return true;
        } else {
            throw new InvalidBookingIDException("Error: Booking ID " + bookingId + " is invalid.");
        }
    }

    public Booking getBookingDetails(int bookingId) throws InvalidBookingIDException {
        if (bookings.containsKey(bookingId)) {
            return bookings.get(bookingId);
        } else {
            throw new InvalidBookingIDException("Error: Booking ID " + bookingId + " is invalid.");
        }
    }
}