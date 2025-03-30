package com.assignment.entity;

public class Booking {
    private Event event;
    private Customer customer;
    private int numTickets;
    private double totalCost;

    public Booking(Customer customer, Event event, int numTickets, double totalCost) {
        this.customer = customer;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
    }
    public double calculate_booking_cost(){
        return numTickets*Event.getTicket_price();

    }
    public void bookTickets(int booktickets) {
        if (event.getAvailable_seats() >= booktickets) {
            event.book_tickets(booktickets);
            this.numTickets += booktickets;
            totalCost = calculate_booking_cost();
            System.out.println("Tickets booked successfully!");
        } else {
            System.out.println("Not enough tickets available!");
        }
    }

    public void cancelBooking(int cancelTickets) {
        if (numTickets >= cancelTickets) {
            event.cancel_booking(cancelTickets);
            this.numTickets -= cancelTickets;
            totalCost = calculate_booking_cost();
            System.out.println("Tickets canceled successfully!");
        } else {
            System.out.println("You don't have enough booked tickets to cancel!");
        }

    }

    public int getAvailableNoOfTickets() {
        return event.getAvailable_seats();
    }

}
