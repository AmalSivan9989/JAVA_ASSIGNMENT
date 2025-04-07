package com.hexaware.entity;

public class Event {
    private String eventName;
    private String eventDate;
    private String eventTime;
    private Venue venue;
    private int totalSeats;
    private int availableSeats;
    private static double ticketPrice;
    private String eventType;
    private int eventId;
    private Booking booking;

    public Event(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.totalSeats = totalSeats;
        Event.ticketPrice = ticketPrice;
        this.eventType = eventType;
        this.venue = venue;

    }

    public Event() {
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Event(String eventName) {
        this.eventName = eventName;
    }


    public Event(String eventName, String eventDate, String eventTime, Venue venue, int totalSeats, int availableSeats, double ticketPrice, String eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    public Event(int eventId, String eventName, Venue venue, int totalSeats) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.venue = venue;
        this.totalSeats = totalSeats;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public static double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public int getBookedNoOfTickets(){

        return totalSeats - availableSeats;
    }
    public double calculate_total_revenue(){
        return getBookedNoOfTickets() * getTicketPrice();
    }
    public void bookTickets(int num_tickets){
        if(getAvailableSeats()>=num_tickets) {

            setAvailableSeats(getAvailableSeats() - num_tickets);
            System.out.println("available tickets:"+getAvailableSeats());
        }
        else{
            System.out.println("Tickets are not available!");

        }}
    public void cancelBooking(int num_tickets) {
        if (getAvailableSeats() + num_tickets <= getTotalSeats()) {
            setAvailableSeats(getAvailableSeats() + num_tickets);
            System.out.println("Booking canceled! Available seats: " + getAvailableSeats());
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "availableSeats=" + availableSeats +
                ", eventName='" + eventName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", venue=" + venue +
                ", totalSeats=" + totalSeats +
                ", ticketPrice=" + ticketPrice +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
