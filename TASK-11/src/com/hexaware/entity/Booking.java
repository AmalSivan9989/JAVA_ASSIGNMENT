package com.hexaware.entity;

import java.util.List;
import java.util.Map;

public class Booking {
    Event event;
    Customer customer;
    private int numTickets;
    private double totalCost;
    private static int idCounter = 1000;
    private int bookingId;
    private List<Customer> customers;
    private String eventName;
    private int numberOfTickets;

    public Booking(Event event, Customer customer, int numTickets, double totalCost) {
        this.event = event;
        this.customer = customer;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
    }
    public Booking(int bookingId, List<Customer> customers, Event event, int numTickets) {
        this.bookingId = bookingId;
        this.customers = customers;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = numTickets * event.getTicketPrice(); // auto-calculation
    }

    public Booking() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customer=" + customer +
                ", event=" + event +
                ", numTickets=" + numTickets +
                ", totalCost=" + totalCost +
                '}';
    }
    public double calculate_booking_cost(){
        return numTickets*Event.getTicketPrice();
    }
    public void bookTickets(int booktickets) {
        if (event.getAvailableSeats() >= booktickets) {
            event.bookTickets(booktickets);
            this.numTickets += booktickets;
            totalCost = calculate_booking_cost();
            System.out.println("Tickets booked successfully!");
        } else {
            System.out.println("Not enough tickets available!");
        }
    }

    public void cancelBooking(int cancelTickets) {
        if (numTickets >= cancelTickets) {
            event.cancelBooking(cancelTickets);
            this.numTickets -= cancelTickets;
            totalCost = calculate_booking_cost();
            System.out.println("Tickets canceled successfully!");
        } else {
            System.out.println("You don't have enough booked tickets to cancel!");
        }

    }

    public int getAvailableNoOfTickets() {
        return event.getAvailableSeats();
    }
    public int getBookingId() {
        return bookingId;
    }
}
