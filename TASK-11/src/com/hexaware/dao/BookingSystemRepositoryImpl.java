package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;
import com.hexaware.exception.DbConnectionException;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;
import com.hexaware.util.DBUtil;
import com.hexaware.util.HexaConstants;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

    private List<Booking> bookings = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private int bookingCounter = 1;

    @Override
    public double calculateBookingCost(int numTickets) {

        if (events.isEmpty()) return 0;
        Event event = events.get(0);
        return numTickets * event.getTicketPrice();
    }

    @Override
    public Booking bookTickets(String eventName, int numTickets, List<Customer> customers) throws DbConnectionException, EventNotFoundException {
        Connection conn = null;
        Booking booking = null;

        try {
            conn = DBUtil.getDbConnection();
            eventName = eventName.trim();


            PreparedStatement st1 = conn.prepareStatement(HexaConstants.FIND_EVENT_BY_NAME_QRY);
            st1.setString(1, eventName);
            ResultSet rs = st1.executeQuery();

            if (!rs.next()) {
                throw new EventNotFoundException("Event with name '" + eventName + "' not found.");
            }

            int availableSeats = rs.getInt("available_seats");
            int eventId = rs.getInt("event_id");
            BigDecimal ticketPrice = rs.getBigDecimal("ticket_price");

            if (availableSeats >= numTickets) {

                List<Integer> customerIds = new ArrayList<>();
                for (Customer customer : customers) {
                    PreparedStatement custSt = conn.prepareStatement(HexaConstants.INSERT_CUSTOMER_QRY, Statement.RETURN_GENERATED_KEYS);
                    custSt.setString(1, customer.getCustomerName());
                    custSt.setString(2, customer.getEmail());
                    custSt.setString(3, customer.getPhoneNumber());
                    custSt.executeUpdate();

                    ResultSet custKeys = custSt.getGeneratedKeys();
                    if (custKeys.next()) {
                        int customerId = custKeys.getInt(1);
                        customerIds.add(customerId);
                        customer.setCustomerId(customerId);
                    }
                }


                BigDecimal totalCost = ticketPrice.multiply(new BigDecimal(numTickets));

                PreparedStatement insertSt = conn.prepareStatement(HexaConstants.INSERT_BOOKING_QRY, Statement.RETURN_GENERATED_KEYS);
                insertSt.setInt(1, eventId);
                insertSt.setInt(2, customerIds.get(0));
                insertSt.setInt(3, numTickets);
                insertSt.setBigDecimal(4, totalCost);
                insertSt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
                insertSt.executeUpdate();

                ResultSet genKeys = insertSt.getGeneratedKeys();
                if (genKeys.next()) {
                    int bookingId = genKeys.getInt(1);


                    for (Integer customerId : customerIds) {
                        PreparedStatement linkSt = conn.prepareStatement(HexaConstants.LINK_CUSTOMER_BOOKING_QRY);
                        linkSt.setInt(1, bookingId);
                        linkSt.setInt(2, customerId);
                        linkSt.executeUpdate();
                    }

                    Event event = new Event();
                    event.setEventId(eventId);
                    event.setEventName(eventName);

                    booking = new Booking(bookingId, customers, event, numTickets);
                }
            } else {
                System.out.println("Not enough seats available.");
            }
        } catch (SQLException e) {
            throw new DbConnectionException(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }

        return booking;
    }

    @Override
    public boolean cancelBooking(int bookingId) throws DbConnectionException, InvalidBookingIDException {
        Connection conn = null;
        try {
            conn = DBUtil.getDbConnection();


            PreparedStatement st = conn.prepareStatement(HexaConstants.GET_BOOKING_DETAILS_QRY);
            st.setInt(1, bookingId);
            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                throw new InvalidBookingIDException("No booking found with ID: " + bookingId);
            }


            int numTickets = rs.getInt("num_tickets");
            String eventName = rs.getString("event_name");


            PreparedStatement updateSeats = conn.prepareStatement(HexaConstants.RESTORE_SEATS_QRY);
            updateSeats.setInt(1, numTickets);
            updateSeats.setString(2, eventName);
            updateSeats.executeUpdate();


            PreparedStatement delete = conn.prepareStatement(HexaConstants.DELETE_BOOKING_QRY);
            delete.setInt(1, bookingId);
            delete.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new DbConnectionException("Error while canceling the booking: " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }
    }

    @Override
    public Booking getBookingDetails(int bookingId) throws DbConnectionException, InvalidBookingIDException {
        Connection conn = null;
        Booking booking = null;
        try {
            conn = DBUtil.getDbConnection();
            PreparedStatement st = conn.prepareStatement(HexaConstants.GET_BOOKING_DETAILS_QRY);
            st.setInt(1, bookingId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int numTickets = rs.getInt("num_tickets");
                BigDecimal totalCost = rs.getBigDecimal("total_cost");
                Date bookingDate = rs.getDate("booking_date");
                String eventName = rs.getString("event_name");
                String customerName = rs.getString("customer_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");


                Customer customer = new Customer(customerName, email, phone);
                List<Customer> customerList = new ArrayList<>();
                customerList.add(customer);

                Event event = new Event();
                event.setEventName(eventName);


                booking = new Booking(bookingId, customerList, event, numTickets);
                booking.setTotalCost(totalCost.doubleValue());
                booking.setBookingDate(bookingDate.toLocalDate());

            } else {
                throw new InvalidBookingIDException("Booking ID " + bookingId + " not found.");
            }

        } catch (SQLException e) {
            throw new DbConnectionException("Database error: " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }
        return booking;
    }

    @Override
    public Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue) throws DbConnectionException {
        Connection conn = null;
        Event event = null;
        try {
            conn = DBUtil.getDbConnection();
            PreparedStatement venueStmt = conn.prepareStatement(
                    "INSERT INTO venue (venue_name, address) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            venueStmt.setString(1, venue.getVenueName());
            venueStmt.setString(2, venue.getAddress());
            venueStmt.executeUpdate();

            ResultSet venueRs = venueStmt.getGeneratedKeys();
            int venueId = 0;
            if (venueRs.next()) {
                venueId = venueRs.getInt(1);
            }
            venue.setVenueId(venueId);

            event = new Event(eventName, eventDate, eventTime, totalSeats, ticketPrice, eventType, venue);

            PreparedStatement st = conn.prepareStatement(HexaConstants.CREATE_EVENT_FULL_QRY, Statement.RETURN_GENERATED_KEYS);
            event.setAvailableSeats(totalSeats);
            //st.setInt(1, 0);
            st.setString(1, event.getEventName());
            st.setString(2, event.getEventDate());
            st.setString(3, event.getEventTime());
            st.setInt(4, event.getVenue().getVenueId());
            st.setInt(5, event.getTotalSeats());
            st.setInt(6, event.getAvailableSeats());
            st.setDouble(7, event.getTicketPrice());
            st.setString(8, event.getEventType());
            st.setNull(9, java.sql.Types.INTEGER);


            int rows = st.executeUpdate();
            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int generatedEventId = rs.getInt(1);
                    event.setEventId(generatedEventId); // assuming you have setEventId()
                }
            }
        } catch (SQLException e) {
            throw new DbConnectionException(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }
        return event;
    }
    @Override
    public List<Event> getEventDetails() throws DbConnectionException {
        Connection conn = null;
        List<Event> eventList = new ArrayList<>();

        try {
            conn = DBUtil.getDbConnection();
            PreparedStatement st = conn.prepareStatement(HexaConstants.GET_ALL_EVENTS_QRY);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String eventName = rs.getString("event_name");
                int venueId = rs.getInt("venue_id");
                int availableSeats = rs.getInt("available_seats");
                double ticketPrice = rs.getDouble("ticket_price");
                String eventType = rs.getString("event_type");


                Event event = new Event(eventId, eventName, venueId, availableSeats, ticketPrice, eventType);
                eventList.add(event);
            }
        } catch (SQLException e) {
            throw new DbConnectionException("Error retrieving event details: " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }

        return eventList;
    }

    @Override
    public int getAvailableSeats(String eventName) throws DbConnectionException {
        Connection conn = null;
        int seats = 0;

        try {
            conn = DBUtil.getDbConnection();
            PreparedStatement st = conn.prepareStatement(HexaConstants.GET_AVAILABLE_SEATS_QRY);
            st.setString(1, eventName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                seats = rs.getInt("available_seats");
            }
        } catch (SQLException e) {
            throw new DbConnectionException(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }
        return seats;
    }
    public List<Event> getAllEvents() throws DbConnectionException {
        Connection conn = null;
        List<Event> events = new ArrayList<>();
        try {
            conn = DBUtil.getDbConnection();
            PreparedStatement st = conn.prepareStatement(HexaConstants.GET_ALL_EVENTS_QRY);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("event_id");
                String name = rs.getString("event_name");
                int venueId = rs.getInt("venue_id");
                int seats = rs.getInt("available_seats");
                events.add(new Event(id, name, new Venue(venueId), seats));
            }
        } catch (SQLException e) {
            throw new DbConnectionException(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }
        return events;
    }

}