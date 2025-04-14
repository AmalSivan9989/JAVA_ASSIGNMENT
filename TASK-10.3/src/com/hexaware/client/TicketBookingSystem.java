package com.hexaware.client;

import com.hexaware.dao.BookingSystemServiceProviderImpl;
import com.hexaware.dao.EventServiceProviderImpl;
import com.hexaware.entity.*;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

import java.util.*;

public class TicketBookingSystem {
    public static void main(String[] args) throws InvalidBookingIDException {
        Scanner scanner = new Scanner(System.in);

        EventServiceProviderImpl eventService = new EventServiceProviderImpl();
        BookingSystemServiceProviderImpl bookingService = new BookingSystemServiceProviderImpl(eventService);

        while (true) {
            System.out.println("\nAvailable Commands:");
            System.out.println("1. Create Event");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Get Event Details");
            System.out.println("5. Get Available Seats");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter venue name: ");
                    String venueName = scanner.nextLine();
                    System.out.print("Enter venue location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter venue capacity: ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter total seats: ");
                    int totalSeats = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter ticket price: ");
                    double ticketPrice = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter event type (Movie, Sports, Concert): ");
                    String eventType = scanner.nextLine();

                    Venue venue = new Venue(venueName, location, capacity);
                    Event event = eventService.createEvent(eventName, "2025-04-04", "18:00", totalSeats, ticketPrice, eventType, venue);
                    System.out.println("Event created successfully: " + event.getEventName());
                    break;

                case 2:
                    System.out.print("Enter event name to book tickets: ");
                    String bookEventName = scanner.nextLine();
                    System.out.print("Enter number of tickets: ");
                    int numTickets = Integer.parseInt(scanner.nextLine());

                    Map<Integer, Customer> customers = new HashMap<>();
                    for (int i = 1; i <= numTickets; i++) {
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Enter customer email: ");
                        String customerEmail = scanner.nextLine();
                        customers.put(i, new Customer(customerName, customerEmail));
                    }

                    try {
                        Booking booking = bookingService.bookTickets(bookEventName, numTickets, customers);
                        if (booking != null) {
                            System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
                        }
                    } catch (EventNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter booking ID to cancel: ");
                    int bookingId = Integer.parseInt(scanner.nextLine());

                    if (bookingService.cancelBooking(bookingId)) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Booking ID not found.");
                    }
                    break;

                case 4:
                    Map<String, Event> eventMap = eventService.getEventDetails();
                    if (eventMap.isEmpty()) {
                        System.out.println("No events available.");
                    } else {
                        for (Event e : eventMap.values()) {
                            e.printEventDetails();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Total available tickets: " + eventService.getAvailableNoOfTickets());
                    break;

                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}