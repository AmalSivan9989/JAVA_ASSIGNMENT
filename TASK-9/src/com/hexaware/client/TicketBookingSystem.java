package com.hexaware.client;

import com.hexaware.entity.*;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystemServiceProviderImpl bookingService = new BookingSystemServiceProviderImpl();

        EventServiceProviderImpl eventServiceProvider = new EventServiceProviderImpl();
        bookingService.createEvent(
                "amal", "2025-04-04", "18:00", 50, 250.0, "Concert", new Venue("Chennai", "Main Hall")
        );

        while (true) {
            System.out.println("\n--- Ticket Booking System ---");
            System.out.println("1. Book Tickets");
            System.out.println("2. Cancel Tickets");
            System.out.println("3. Get Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter event name: ");
                        String eventName = scanner.nextLine();
                        System.out.print("Enter number of tickets: ");
                        int numTickets = Integer.parseInt(scanner.nextLine());

                        // Creating dummy customers for simplicity
                        List<Customer> customers = new ArrayList<>();
                        for (int i = 0; i < numTickets; i++) {
                            customers.add(new Customer("Customer" + (i + 1), "customer" + (i + 1) + "@mail.com"));
                        }

                        try {
                            Booking booking = bookingService.bookTickets(eventName, numTickets, customers);
                            if (booking != null) {
                                System.out.println("Booking Successful! Booking ID: " + booking.getBookingId());
                            }
                        } catch (EventNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Enter booking ID to cancel: ");
                        int bookingId = Integer.parseInt(scanner.nextLine());

                        if (bookingService.cancelBooking(bookingId)) {
                            System.out.println("Booking Cancelled Successfully.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter booking ID to view details: ");
                        int viewBookingId = Integer.parseInt(scanner.nextLine());

                        Booking booking = bookingService.getBookingDetails(viewBookingId);
                        System.out.println("Booking Details: " + booking);
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            } catch (NullPointerException e) {
                System.out.println("Error: Null value encountered.");
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
    }
}