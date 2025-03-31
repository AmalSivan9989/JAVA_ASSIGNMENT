package com.hexaware.client;

import com.hexaware.entity.Event;
import com.hexaware.entity.TicketBookingSystem;

import java.util.Scanner;

public class TicketBookingSystemClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketBookingSystem system = new TicketBookingSystem();

        while (true) {
            System.out.println("\n1: Create Event\n2: Display Events\n3: Book Tickets\n4: Cancel Tickets\n5: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Event Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Time: ");
                    String time = scanner.nextLine();
                    System.out.print("Enter Total Seats: ");
                    int seats = scanner.nextInt();
                    System.out.print("Enter Ticket Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Event Type (Movie, Concert, Sport): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Venue: ");
                    String venue = scanner.nextLine();
                    String sportName = "", teams = "";
                    if (type.equalsIgnoreCase("sport")) {
                        System.out.print("Enter Sport Name: ");
                        sportName = scanner.nextLine();
                        System.out.print("Enter Teams: ");
                        teams = scanner.nextLine();
                    }
                    system.createEvent(name, date, time, seats, price, type, venue, sportName, teams);
                    break;
                case 2:
                    for (Event event : system.getEvents()) {
                        system.display_event_details(event);
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
