package com.hexaware.client;


import com.hexaware.entity.Event;

import java.util.Scanner;

import static com.hexaware.entity.TicketBookingSystem.*;

public class TicketBookingSystemClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create sample events
        Event movie = create_event("Avengers", "15-07-2025", "6:00 ", 100, 250.0, "Movie", "Theater");
        Event sports = create_event("Football Match", "20-07-2025", "8:00 ", 150, 500.0, "Sports", "Stadium");
        Event concert = create_event("Rock Night", "25-07-2025", "9:00", 200, 750.0, "Concert", "Arena");

        boolean running = true;
        while (running) {
            System.out.println("\n1: View Event Details\n2: Book Tickets\n3: Cancel Tickets\n4: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nSelect Event:\n1. Movie\n2. Sports\n3. Concert");
                    int eventChoice = scanner.nextInt();
                    if (eventChoice == 1) {
                        display_event_details(movie);
                    }

                    else if (eventChoice == 2){
                        display_event_details(sports);
                    }
                    else if (eventChoice == 3){
                        display_event_details(concert);
                    }
                    else System.out.println("Invalid choice!");
                    break;
                case 2:
                    System.out.println("\nSelect Event:\n1. Movie\n2. Sports\n3. Concert");
                    eventChoice = scanner.nextInt();
                    System.out.print("Enter number of tickets: ");
                    int num_tickets = scanner.nextInt();
                    double cost = 0;
                    if (eventChoice == 1) {
                        cost = book_tickets(movie, num_tickets);
                    }
                    else if (eventChoice == 2){
                        cost = book_tickets(sports, num_tickets);
                    }
                    else if (eventChoice == 3) {
                        cost = book_tickets(concert, num_tickets);
                    }


                    if (cost > 0) {
                        System.out.println("Total cost: " + cost);
                    }
                    break;
                case 3:
                    System.out.println("\nSelect Event:\n1. Movie\n2. Sports\n3. Concert");
                    eventChoice = scanner.nextInt();
                    System.out.print("Enter number of tickets to cancel: ");
                    int cancel_tickets = scanner.nextInt();
                    if (eventChoice == 1) {
                        cancel_tickets(movie, cancel_tickets);
                    }
                    else if (eventChoice == 2){
                        cancel_tickets(sports, cancel_tickets);
                    }
                    else if (eventChoice == 3){
                        cancel_tickets(concert, cancel_tickets);
                    }

                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

    }
}
