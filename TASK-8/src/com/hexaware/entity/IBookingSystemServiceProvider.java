package com.hexaware.entity;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(int numTickets, double ticketPrice);
    Booking bookTickets(String eventName, int numTickets, Customer[] customers);
    boolean cancelBooking(int bookingId);
    Booking getBookingDetails(int bookingId);
}
