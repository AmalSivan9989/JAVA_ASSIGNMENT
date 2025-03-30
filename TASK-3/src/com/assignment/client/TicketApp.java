package com.assignment.client;
import static com.assignment.entity.TicketCost.costOfTickets;
import static com.assignment.entity.TicketCheck.ticketCheck;
import java.util.Scanner;

public class TicketApp {
    private static double price;
    static double totalPrice;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        char ch;

        do {
            System.out.print("Do you want to book tickets:(y/n):");
             ch=sc.next().charAt(0);
            if (ch == 'n' || ch == 'N') {
                System.out.println("Thank you! Have a nice day.");
                break;
            }

            System.out.print("Enter the preferred Class:");
            String Class = sc.next();
            System.out.print("Enter the total Available Tickets:");
            int availableTicket = sc.nextInt();
            System.out.print("Enter the no of Tickets need to Book:");
            int noOfBookingTicket = sc.nextInt();
            if (ticketCheck(availableTicket, noOfBookingTicket)) {
                System.out.println("Tickets are available!. Please continue booking");
                double res = costOfTickets(Class, noOfBookingTicket);
                if (res > 0) {
                    System.out.println("Your entered Class is: " + Class + " And the total Cost is: " + res);
                } else {
                    System.out.println("RECHECK! the class");
                }
            } else {
                System.out.println("Tickets are not available!");
                System.out.println("Tickets available are:" + availableTicket);
            }
        }while(ch=='Y'||ch=='y');

    }



}
