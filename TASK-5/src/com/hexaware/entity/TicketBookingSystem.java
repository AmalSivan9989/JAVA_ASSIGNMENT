package com.hexaware.entity;

public class TicketBookingSystem {

    public static Event create_event(String event_name, String date, String time, int total_seats, double ticket_price, String event_type, String venue_name) {
        return new Event(total_seats, date, event_name, event_type, ticket_price, total_seats, venue_name, time);
    }


    public static void display_event_details(Event event) {
        event.display_event_details();
    }


    public static double book_tickets(Event event, int num_tickets) {
        if (event.getAvailable_seats() >= num_tickets) {
            event.book_tickets(num_tickets);
            return num_tickets * Event.getTicket_price();
        } else {
            System.out.println("Tickets are not available!");
            return 0.0;
        }
    }


    public static void cancel_tickets(Event event, int num_tickets) {
        event.cancel_booking(num_tickets);
    }

}
