package com.hexaware.client;

import com.hexaware.entity.BookingSystemServiceProviderImpl;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;

import java.util.Scanner;

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystemServiceProviderImpl system = new BookingSystemServiceProviderImpl();

        Venue venue = new Venue("Grand Arena", "Downtown");
        system.createEvent("Rock Concert", "2025-04-01", "18:00", 100, 50.0, "Concert", venue);

        while (true) {
            System.out.println("\nEnter command (create_event, book_tickets, cancel_tickets, get_available_seats, get_event_details, exit):");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) break;
            else if (command.equalsIgnoreCase("get_event_details")) system.getEventDetails().forEach(Event::displayEventDetails);
            else if (command.equalsIgnoreCase("get_available_seats")) System.out.println("Total available seats: " + system.getAvailableNoOfTickets());
        }
        scanner.close();
    }
}
