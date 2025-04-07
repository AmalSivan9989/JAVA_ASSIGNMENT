package com.hexaware.client;

import com.hexaware.dao.BookingSystemRepositoryImpl;
import com.hexaware.dao.IBookingSystemRepository;
import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;
import com.hexaware.exception.DbConnectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBookingSystem  {
    public static void main(String[] args) {



    BookingSystemRepositoryImpl repo = new BookingSystemRepositoryImpl();
    Scanner sc = new Scanner(System.in);
    int choice;

        do {
            System.out.println("\n===== Ticket Booking System =====");
            System.out.println("1. Create Event");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Get Booking Details");
            System.out.println("5. Exit");
            System.out.println("6. Get Available Seats for an Event");
            System.out.print("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter event name: ");
                String name = sc.nextLine();
                System.out.print("Enter event date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                System.out.print("Enter event time (HH:MM): ");
                String time = sc.nextLine();
                System.out.print("Enter total seats: ");
                int totalSeats = sc.nextInt();
                System.out.print("Enter ticket price: ");
                double price = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter event type: ");
                String eventType = sc.nextLine();
                System.out.print("Enter venue name: ");
                String venueName = sc.nextLine();
                System.out.print("Enter venue location: ");
                String address = sc.nextLine();

                try {

                    Venue venue = new Venue(venueName, address);

                    Event event = repo.createEvent(name, date, time, totalSeats, price, eventType, venue);
                    System.out.println("Event created successfully: " + event.getEventName());
                } catch (DbConnectionException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 2:
                try {
                    // Show all available events before booking
                    List<Event> events = repo.getAllEvents();
                    if (events.isEmpty()) {
                        System.out.println("No events available at the moment.");
                        break;
                    }

                    System.out.println("Available Events:");
                    for (Event e : events) {
                        System.out.println("- " + e.getEventName());
                    }

                    System.out.print("Enter event name: ");
                    String eventToBook = sc.nextLine();
                    System.out.print("Enter number of tickets: ");
                    int numTickets = sc.nextInt();
                    sc.nextLine();

                    List<Customer> customers = new ArrayList<>();
                    for (int i = 1; i <= numTickets; i++) {
                        System.out.print("Enter name of customer " + i + ": ");
                        String custName = sc.nextLine();
                        System.out.print("Enter email of customer " + i + ": ");
                        String custEmail = sc.nextLine();
                        System.out.print("Enter Customer Phone: ");
                        String custPhone = sc.nextLine();
                        customers.add(new Customer(custName, custEmail, custPhone));
                    }

                    Booking booking = repo.bookTickets(eventToBook, numTickets, customers);
                    if (booking != null) {
                        System.out.println("Booking successful! Booking ID: " + booking.getBookingId());
                    } else {
                        System.out.println("Booking failed. Not enough seats or event not found.");
                    }
                } catch (DbConnectionException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 3:
                System.out.print("Enter booking ID to cancel: ");
                int cancelId = sc.nextInt();
                try {
                    boolean success = repo.cancelBooking(cancelId);
                    if (success) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Booking not found.");
                    }
                } catch (DbConnectionException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 4:
                System.out.print("Enter booking ID to view details: ");
                int bookingId = sc.nextInt();
                try {
                    Booking booking = repo.getBookingDetails(bookingId);
                    if (booking != null) {
                        System.out.println("Booking ID: " + booking.getBookingId());
                        System.out.println("Event Name: " + booking.getEvent().getEventName());
                        System.out.println("Number of Tickets: " + booking.getNumTickets());
                    } else {
                        System.out.println("Booking not found.");
                    }
                } catch (DbConnectionException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 5:
                System.out.println("Thank you for using the Ticket Booking System.");
                break;
            case 6:
                System.out.print("Enter event name to check available seats: ");
                String eventName = sc.nextLine();
                try {
                    int availableSeats = repo.getAvailableSeats(eventName);
                    System.out.println("Available seats for event '" + eventName + "': " + availableSeats);
                } catch (DbConnectionException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    } while (choice != 5);

        sc.close();
}
}