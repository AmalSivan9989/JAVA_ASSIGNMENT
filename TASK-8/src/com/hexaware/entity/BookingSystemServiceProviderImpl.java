package com.hexaware.entity;

import java.util.ArrayList;

public class BookingSystemServiceProviderImpl extends EventServiceProviderImpl implements IBookingSystemServiceProvider{
    private ArrayList<Booking> bookings = new ArrayList<>();

    @Override
    public double calculateBookingCost(int numTickets, double ticketPrice) {
        return numTickets * ticketPrice;
    }

    @Override
    public Booking bookTickets(String eventName, int numTickets, Customer[] customers) {
        for (Event event : events) {
            if (event.eventName.equalsIgnoreCase(eventName) && event.bookTickets(numTickets)) {
                Booking booking = new Booking(customers, event, numTickets);
                bookings.add(booking);
                return booking;
            }
        }
        return null;
}
    @Override
    public boolean cancelBooking(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                booking.event.cancelBooking(booking.numTickets);
                bookings.remove(booking);
                return true;
            }
        }
        return false;
    }

    @Override
    public Booking getBookingDetails(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }}
