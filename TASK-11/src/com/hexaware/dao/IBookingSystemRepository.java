package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;
import com.hexaware.exception.DbConnectionException;

import java.util.List;

public interface IBookingSystemRepository {
    public double calculateBookingCost(int numTickets);
    Booking bookTickets(String eventName, int numTickets, List<Customer> customers) throws DbConnectionException;
    public boolean cancelBooking(int bookingId) throws DbConnectionException;
    Booking getBookingDetails(int bookingId) throws DbConnectionException;
    public abstract Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue) throws DbConnectionException;
    public List<Event> getEventDetails();
    public abstract int getAvailableSeats(String eventName) throws DbConnectionException;
static  IBookingSystemRepository getInstance(){
    return new BookingSystemRepositoryImpl();
}


}
