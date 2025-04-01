package com.hexaware.client;

import com.hexaware.entity.Event;
import com.hexaware.entity.TicketBookingSystem;
import com.hexaware.entity.Venue;

import java.util.Scanner;

public class TicketBookingSystemClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketBookingSystem system = new TicketBookingSystem();

        while (true) {
            System.out.println("\n1: Create Event\n2: Display Events\n3: Exit");
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
                    System.out.print("Enter Venue Name: ");
                    String venueName = scanner.nextLine();
                    System.out.print("Enter Venue Address: ");
                    String address = scanner.nextLine();
                    Venue venue = new Venue(venueName, address);
                    system.createEvent(name, date, time, seats, price, type, venue, "", "");
                    break;
                case 2:
                    for (Event event : system.getEvents()) {
                        event.displayEventDetails();
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    }

