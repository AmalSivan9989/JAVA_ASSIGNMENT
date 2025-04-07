package com.hexaware.util;

public class HexaConstants {
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_FILE_NAME ="TASK-11/hexa.properties";
    public static final String CANNOT_OPEN_CONNECTION = "Cannot Open Connection";
    public static final String FIND_EVENT_BY_NAME_QRY = "SELECT event_id, event_name, available_seats, ticket_price FROM event WHERE event_name = ?";
    public static final String UPDATE_SEATS_QRY = "UPDATE event SET available_seats = available_seats - ? WHERE event_id = ?";
    public static final String INSERT_BOOKING_QRY =
            "INSERT INTO booking (event_id, num_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?)";

    public static final String GET_BOOKING_DETAILS_QRY =
            "SELECT b.booking_id, b.num_tickets, b.total_cost, b.booking_date, " +
                    "e.event_name, c.customer_name, c.email, c.phone_number " +
                    "FROM booking b " +
                    "JOIN event e ON b.event_id = e.event_id " +
                    "JOIN booking_customer bc ON b.booking_id = bc.booking_id " +
                    "JOIN customer c ON bc.customer_id = c.customer_id " +
                    "WHERE b.booking_id = ?";
    public static final String RESTORE_SEATS_QRY = "UPDATE event SET available_seats = available_seats + ? WHERE event_name = ?";
    public static final String DELETE_BOOKING_QRY = "DELETE FROM booking WHERE booking_id = ?";
    public static final String GET_AVAILABLE_SEATS_QRY =
            "SELECT available_seats FROM event WHERE event_name = ?";
    public static final String GET_ALL_EVENTS_QRY = "SELECT * FROM event";
    public static final String CREATE_EVENT_QRY = "INSERT INTO event (event_name, venue_id, available_seats) VALUES (?, ?, ?)";
    public static final String CREATE_EVENT_FULL_QRY =
            "INSERT INTO event (" +
                    "event_id, event_name, event_date, event_time, venue_id, total_seats, " +
                    "available_seats, ticket_price, event_type, booking_id" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_CUSTOMER_QRY =
            "INSERT INTO customer (customer_name, email, phone_number) VALUES (?, ?, ?)";

    public static final String LINK_CUSTOMER_BOOKING_QRY =
            "INSERT INTO booking_customer (booking_id, customer_id) VALUES (?, ?)";

}
