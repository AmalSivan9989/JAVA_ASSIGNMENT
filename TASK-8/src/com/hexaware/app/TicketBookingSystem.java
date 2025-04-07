package com.hexaware.app;

import com.hexaware.bean.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBookingSystem {
    public static void main(String[] args) {
        EventServiceProviderImpl eventService = new EventServiceProviderImpl();
        BookingSystemServiceProviderImpl bookingService = new BookingSystemServiceProviderImpl(eventService);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Ticket Booking System");

        while (true) {
            System.out.println("\nAvailable Commands:");
            System.out.println("1. create_event");
            System.out.println("2. book_tickets");
            System.out.println("3. cancel_tickets");
            System.out.println("4. get_event_details");
            System.out.println("5. get_available_seats");
            System.out.println("6. exit");
            System.out.print("Enter command: ");

            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "create_event":
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter venue name: ");
                    String venueName = scanner.nextLine();
                    System.out.print("Enter venue location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter venue capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter total seats: ");
                    int totalSeats = scanner.nextInt();
                    System.out.print("Enter ticket price: ");
                    double ticketPrice = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter event type (Movie, Sports, Concert): ");
                    String eventType = scanner.nextLine();

                    Venue venue = new Venue(venueName, location, capacity);
                    Event event = eventService.createEvent(eventName, "2025-04-04", "18:00", totalSeats, ticketPrice, eventType, venue);
                    System.out.println("Event created successfully: " + event.getEventName());
                    break;

                case "book_tickets":
                    System.out.print("Enter event name to book tickets: ");
                    String bookEventName = scanner.nextLine();
                    System.out.print("Enter number of tickets: ");
                    int numTickets = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    List<Customer> customers = new ArrayList<>();
                    for (int i = 0; i < numTickets; i++) {
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Enter customer email: ");
                        String customerEmail = scanner.nextLine();
                        customers.add(new Customer(customerName, customerEmail));
                    }

                    Booking booking = bookingService.bookTickets(bookEventName, numTickets, customers);
                    if (booking != null) {
                        System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
                    } else {
                        System.out.println("Booking failed. Event not found or insufficient seats.");
                    }
                    break;

                case "cancel_tickets":
                    System.out.print("Enter booking ID to cancel: ");
                    int bookingId = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (bookingService.cancelBooking(bookingId)) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Booking ID not found.");
                    }
                    break;

                case "get_event_details":
                    List<Event> events = eventService.getEventDetails();
                    if (events.isEmpty()) {
                        System.out.println("No events available.");
                    } else {
                        for (Event e : events) {
                            e.printEventDetails();
                        }
                    }
                    break;

                case "get_available_seats":
                    System.out.println("Total available tickets: " + eventService.getAvailableNoOfTickets());
                    break;

                case "exit":
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}