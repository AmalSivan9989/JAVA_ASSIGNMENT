package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

import java.util.ArrayList;
import java.util.List;

public  class BookingSystemServiceProviderImpl extends EventServiceProviderImpl implements IBookingSystemServiceProvider {
    private List<Booking> bookings;
    private int bookingCounter;

    public BookingSystemServiceProviderImpl() {
        this.bookings = new ArrayList<>();
        this.bookingCounter = 1;
    }

    @Override
    public double calculateBookingCost(int numTickets, double ticketPrice) {
        return numTickets * ticketPrice;
    }

    @Override
    public Booking bookTickets(String eventName, int numTickets, List<Customer> customers) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        if (event == null) {
            throw new EventNotFoundException("Error: Event '" + eventName + "' not found.");
        }

        if (event.getAvailableSeats() < numTickets) {
            System.out.println("Error: Not enough available seats.");
            return null;
        }


        event.setAvailableSeats(event.getAvailableSeats() - numTickets);


        double totalCost = calculateBookingCost(numTickets, event.getTicketPrice());


        Booking newBooking = new Booking(bookingCounter++, event, numTickets, totalCost, customers);
        bookings.add(newBooking);

        return newBooking;
    }

    @Override
    public boolean cancelBooking(int bookingId) throws InvalidBookingIDException {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                // Restore seats
                booking.getEvent().setAvailableSeats(booking.getEvent().getAvailableSeats() + booking.getNumTickets());
                bookings.remove(booking);
                return true;
            }
        }
        throw new InvalidBookingIDException("Error: Booking ID " + bookingId + " is invalid.");
    }

    @Override
    public Booking getBookingDetails(int bookingId) throws InvalidBookingIDException {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        throw new InvalidBookingIDException("Error: Booking ID " + bookingId + " is invalid.");
    }

    private Event findEventByName(String eventName) {
        for (Event event : getEventDetails()) {
            if (event.getEventName().equalsIgnoreCase(eventName)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public int getAvailableSeats(String eventName) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        if (event == null) {
            throw new EventNotFoundException("Error: Event '" + eventName + "' not found.");
        }
        return event.getAvailableSeats();
    }
}