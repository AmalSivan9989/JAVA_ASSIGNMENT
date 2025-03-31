package com.hexaware.entity;

public class TicketBookingSystem extends BookingSystem {


    @Override
    public double bookTickets(Event event, int numTickets) {
        if (event.bookTickets(numTickets)) {
            return numTickets * event.getTicketPrice();
        } else {
            System.out.println("Not enough seats available!");
            return 0;
        }
    }

    @Override
    public void cancelTickets(Event event, int numTickets) {
        event.cancelTickets(numTickets);
    }

    @Override
    public void display_event_details(Event event) {
        event.displayEventDetails();
    }

    @Override
    public Event createEvent(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, String venueName, String additional1, String additional2) {
        Event event = null;
        switch (eventType.toLowerCase()) {
            case "movie":
                event = new Movie(eventName, eventDate, eventTime, totalSeats, ticketPrice, venueName, additional1, additional2, "Unknown Genre");
                break;
            case "concert":
                event = new Concert(eventName, eventDate, eventTime, totalSeats, ticketPrice, venueName, additional1, additional2);
                break;
            case "sport":
                event = new Sport(eventName, eventDate, eventTime, totalSeats, ticketPrice, venueName, additional1, additional2);
                break;
            default:
                System.out.println("Invalid event type!");
                return null;
        }
        events.add(event);
    return event;

    }
}




