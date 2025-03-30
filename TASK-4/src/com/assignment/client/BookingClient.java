package com.assignment.client;

import com.assignment.entity.Booking;
import com.assignment.entity.Customer;
import com.assignment.entity.Event;
import com.assignment.entity.Venue;

import java.util.Scanner;

public class BookingClient {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Event event = new Event(50,"12-03-23", "Rock Concert", "Concert", 100.0, 50, "Music Hall", "7:00 PM");
        Venue venue = new Venue("near park", "Auditorium");
        Customer customer = new Customer("John Abraham", "john@gmail.com", 987654321);

        event.display_event_details();
        venue.display_venue_details();
        customer.display_customer_details();




        Booking booking = new Booking(customer, event, 0, 0.0);

        boolean running = true;
        while (running) {
            System.out.println("\n1: Book Tickets\n2: Cancel Tickets\n3: Check Available Tickets\n4: Calculate Booking Cost\n5: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number of tickets to book: ");
                    int booktickets = scanner.nextInt();
                    booking.bookTickets(booktickets);
                    break;
                case 2:
                    System.out.print("Enter number of tickets to cancel: ");
                    int cancelTickets = scanner.nextInt();
                    booking.cancelBooking(cancelTickets);
                    break;
                case 3:
                    System.out.println("Available tickets: " + booking.getAvailableNoOfTickets());
                    break;
                case 4:
                
                    
                    System.out.println("Total cost: " + booking.calculate_booking_cost());
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
