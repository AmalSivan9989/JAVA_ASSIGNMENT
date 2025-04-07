package com.hexaware.dao;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;
import com.hexaware.exception.DbConnectionException;
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
    public Booking bookTickets(String eventName, int numTickets, List<Customer> customers) throws DbConnectionException {
        Connection conn = null;
        Booking booking = null;

        try {
            conn = DBUtil.getDbConnection();


            PreparedStatement st1 = conn.prepareStatement(HexaConstants.FIND_EVENT_BY_NAME_QRY);
            st1.setString(1, eventName);
            ResultSet rs = st1.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("available_seats");
                int eventId = rs.getInt("event_id");
                BigDecimal ticketPrice = rs.getBigDecimal("ticket_price");

                if (availableSeats >= numTickets) {


                    PreparedStatement updateSt = conn.prepareStatement(HexaConstants.UPDATE_SEATS_QRY);
                    updateSt.setInt(1, numTickets);
                    updateSt.setInt(2, eventId);
                    updateSt.executeUpdate();


                    BigDecimal totalCost = ticketPrice.multiply(new BigDecimal(numTickets));


                    PreparedStatement insertSt = conn.prepareStatement(HexaConstants.INSERT_BOOKING_QRY, Statement.RETURN_GENERATED_KEYS);
                    insertSt.setInt(1, eventId);
                    insertSt.setInt(2, numTickets);
                    insertSt.setBigDecimal(3, totalCost);
                    insertSt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                    insertSt.executeUpdate();

                    ResultSet genKeys = insertSt.getGeneratedKeys();
                    if (genKeys.next()) {
                        int bookingId = genKeys.getInt(1);


                        for (Customer customer : customers) {
                            PreparedStatement custSt = conn.prepareStatement(HexaConstants.INSERT_CUSTOMER_QRY, Statement.RETURN_GENERATED_KEYS);
                            custSt.setString(1, customer.getCustomerName());
                            custSt.setString(2, customer.getEmail());
                            custSt.setString(3, customer.getPhoneNumber());
                            custSt.executeUpdate();

                            ResultSet custKeys = custSt.getGeneratedKeys();
                            if (custKeys.next()) {
                                int customerId = custKeys.getInt(1);

                                PreparedStatement linkSt = conn.prepareStatement(HexaConstants.LINK_CUSTOMER_BOOKING_QRY);
                                linkSt.setInt(1, bookingId);
                                linkSt.setInt(2, customerId);
                                linkSt.executeUpdate();
                            }
                        }


                        Event event = new Event();
                        event.setEventId(eventId);
                        event.setEventName(eventName);

                        booking = new Booking(bookingId, customers, event, numTickets);
                    }

                } else {
                    System.out.println("Not enough seats available.");
                }
            } else {
                System.out.println("Event not found.");
            }

        } catch (SQLException e) {
            throw new DbConnectionException(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }

        return booking;
    }

    @Override
    public boolean cancelBooking(int bookingId) throws DbConnectionException {
        Connection conn = null;
        try {
            conn = DBUtil.getDbConnection();


            PreparedStatement st = conn.prepareStatement(HexaConstants.GET_BOOKING_DETAILS_QRY);
            st.setInt(1, bookingId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
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
            }

        } catch (SQLException e) {
            throw new DbConnectionException(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn);
        }
        return false;
    }

    @Override
    public Booking getBookingDetails(int bookingId) throws DbConnectionException {
        Connection conn = null;
        Booking booking = null;
        try {
            conn = DBUtil.getDbConnection();
            PreparedStatement st = conn.prepareStatement(HexaConstants.GET_BOOKING_DETAILS_QRY);
            st.setInt(1, bookingId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int numTickets = rs.getInt("num_tickets");
                String eventName = rs.getString("event_name");
                booking = new Booking(bookingId, null, new Event(eventName), numTickets);
            }
        } catch (SQLException e) {
            throw new DbConnectionException(e.getMessage());
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

            st.setInt(1, 0);
            st.setString(2, event.getEventName());
            st.setString(3, event.getEventDate());
            st.setString(4, event.getEventTime());
            st.setInt(5, event.getVenue().getVenueId());
            st.setInt(6, event.getTotalSeats());
            st.setInt(7, event.getAvailableSeats());
            st.setDouble(8, event.getTicketPrice());
            st.setString(9, event.getEventType());
            st.setNull(10, java.sql.Types.INTEGER);

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
    public List<Event> getEventDetails() {
        return events;
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