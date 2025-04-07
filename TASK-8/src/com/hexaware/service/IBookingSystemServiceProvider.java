package com.hexaware.service;

import com.hexaware.bean.Booking;
import com.hexaware.bean.Customer;

import java.util.List;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(int numTickets, double ticketPrice);
    Booking bookTickets(String eventName, int numTickets, List<Customer> customers);
    boolean cancelBooking(int bookingId);
    Booking getBookingDetails(int bookingId);
}