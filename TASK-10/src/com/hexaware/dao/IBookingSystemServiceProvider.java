package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

import java.util.List;
import java.util.Set;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(int numTickets, double ticketPrice);

    // Changed List<Customer> to Set<Customer>
    Booking bookTickets(String eventName, int numTickets, Set<Customer> customers) throws EventNotFoundException;

    boolean cancelBooking(int bookingId) throws InvalidBookingIDException;

    Booking getBookingDetails(int bookingId) throws InvalidBookingIDException;
}