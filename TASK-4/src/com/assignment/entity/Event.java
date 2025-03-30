package com.assignment.entity;

import java.util.Scanner;

public class Event {
    private String event_name;
    private String event_date;
    private String event_time;
    private String venue_name;
    private int total_seats;
    private int  available_seats;
    private static double ticket_price;
    private String event_type;


    public Event(){

    }

    public Event(int available_seats, String event_date, String event_name, String event_type, double ticket_price, int total_seats, String venue_name,String event_time) {
        this.available_seats = available_seats;
        this.event_date = event_date;
        this.event_name = event_name;
        this.event_type = event_type;
        this.ticket_price = ticket_price;
        this.total_seats = total_seats;
        this.venue_name = venue_name;
        this.event_time=event_time;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public static double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        Event.ticket_price = ticket_price;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

public int getBookedNoOfTickets(){

    return total_seats - available_seats;
}
    public double calculate_total_revenue(){
        return getBookedNoOfTickets() * getTicket_price();
    }
public void book_tickets(int num_tickets){
        if(getAvailable_seats()>=num_tickets) {

            setAvailable_seats(getAvailable_seats() - num_tickets);
            System.out.println("available tickets:"+getAvailable_seats());
        }
        else{
            System.out.println("Tickets are not available!");

        }
}

    public void cancel_booking(int num_tickets) {
        if (getAvailable_seats() + num_tickets <= getTotal_seats()) {
            setAvailable_seats(getAvailable_seats() + num_tickets);
            System.out.println("Booking canceled! Available seats: " + getAvailable_seats());
        }
}
public void display_event_details(){
    System.out.println("Event Name: " + getEvent_name());
    System.out.println("Date: " + getEvent_date());
    System.out.println("Time: " + getEvent_time());
    System.out.println("Venue: " + getVenue_name());
    System.out.println("Total Seats: " + getTotal_seats());
    System.out.println("Available Seats: " + getAvailable_seats());
    System.out.println("Ticket Price: " + getTicket_price());
    System.out.println("Event Type: " + getEvent_type());
}

    public static void eventMethods(Event en){ // Accept the existing Event object
        Scanner sc=new Scanner(System.in);
        char ch;

        do {
            System.out.println("\nWelcome! What would you like to do?");
            System.out.println("1: Calculate total revenue");
            System.out.println("2: Get total booked tickets");
            System.out.println("3: Book tickets");
            System.out.println("4: Cancel tickets");
            System.out.println("5: Display event details");
            System.out.print("Enter your choice: ");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    double res = en.calculate_total_revenue();
                    System.out.println("Total Revenue: " + res);
                    break;
                case 2:
                    int res1 = en.getBookedNoOfTickets();
                    System.out.println("Total Booked Tickets: " + res1);
                    break;
                case 3:
                    System.out.print("Enter number of tickets to book: ");
                    int num_tickets = sc.nextInt();
                    en.book_tickets(num_tickets);
                    break;
                case 4:
                    System.out.print("Enter number of tickets to cancel: ");
                    int num_tickets1 = sc.nextInt();
                    en.cancel_booking(num_tickets1);
                    break;
                case 5:
                    en.display_event_details();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.print("Do you want to continue? (y/n): ");
            ch = sc.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

}
