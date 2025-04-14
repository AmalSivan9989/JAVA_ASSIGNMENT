package com.hexaware.service;

import com.hexaware.bean.Booking;
import com.hexaware.bean.Customer;
import com.hexaware.bean.Event;

import java.util.ArrayList;
import java.util.List;

public class BookingSystemServiceProviderImpl extends EventServiceProviderImpl implements IBookingSystemServiceProvider {
    private List<Booking> bookings = new ArrayList<>();
    private int bookingCounter = 1;
    private EventServiceProviderImpl eventService;



    public BookingSystemServiceProviderImpl(EventServiceProviderImpl eventService) {
        super(); // Calls the parent constructor (optional)
        this.bookings = new ArrayList<>();
        this.bookingCounter = 1;
        this.eventService = eventService;
    }
    @Override
    public double calculateBookingCost(int numTickets, double ticketPrice) {
        return numTickets * ticketPrice;
    }

    @Override
    public Booking bookTickets(String eventName, int numTickets, List<Customer> customers) {
        Event event = findEventByName(eventName);
        if (event == null || event.getAvailableSeats() < numTickets) {
            System.out.println("Booking failed.");
            return null;
        }

        event.setAvailableSeats(event.getAvailableSeats() - numTickets);
        double totalCost = calculateBookingCost(numTickets, event.getTicketPrice());

        Booking newBooking = new Booking(bookingCounter++, event, numTickets, totalCost, customers);
        bookings.add(newBooking);
        return newBooking;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                booking.getEvent().setAvailableSeats(booking.getEvent().getAvailableSeats() + booking.getNumTickets());
                bookings.remove(booking);
                return true;
            }
        }
        return false;
    }

    @Override
    public Booking getBookingDetails(int bookingId) {
        return bookings.stream().filter(b -> b.getBookingId() == bookingId).findFirst().orElse(null);
    }

    private Event findEventByName(String eventName) {
        return getEventDetails().stream().filter(e -> e.getEventName().equalsIgnoreCase(eventName)).findFirst().orElse(null);
    }
}