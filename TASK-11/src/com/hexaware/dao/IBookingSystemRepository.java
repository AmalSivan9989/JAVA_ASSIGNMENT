package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;
import com.hexaware.exception.DbConnectionException;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

import java.util.List;

public interface IBookingSystemRepository {
    public double calculateBookingCost(int numTickets);
    Booking bookTickets(String eventName, int numTickets, List<Customer> customers) throws DbConnectionException, EventNotFoundException;
    public boolean cancelBooking(int bookingId) throws DbConnectionException, InvalidBookingIDException;
    Booking getBookingDetails(int bookingId) throws DbConnectionException, InvalidBookingIDException;
    public abstract Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue) throws DbConnectionException;
    public List<Event> getEventDetails() throws DbConnectionException;
    public abstract int getAvailableSeats(String eventName) throws DbConnectionException;
static  IBookingSystemRepository getInstance(){
    return new BookingSystemRepositoryImpl();
}


}
