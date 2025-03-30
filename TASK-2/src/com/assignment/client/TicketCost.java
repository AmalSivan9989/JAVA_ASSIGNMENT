package com.assignment.client;
import com.assignment.entity.TicketCheck;
import java.util.Scanner;

import static com.assignment.entity.TicketCheck.ticketCheck;
import static com.assignment.entity.TicketCost.costOfTickets;

public class TicketCost {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the preferred Class:");
        String Class=sc.next();
        System.out.print("Enter the total Available Tickets:");
        int availableTicket=sc.nextInt();
        System.out.print("Enter the no of Tickets need to Book:");
        int noOfBookingTicket=sc.nextInt();
        if(ticketCheck(availableTicket,noOfBookingTicket)){
            System.out.println("Tickets are available!. Please continue booking");
            double res=costOfTickets(Class,noOfBookingTicket);
            if(res>0){
            System.out.println("Your entered Class is: "+Class+" And the total Cost is: "+res);
        }
        else{
                System.out.println("RECHECK! the class");
            }
        }
        else{
            System.out.println("Tickets are not available!");
            System.out.println("Tickets available are:"+availableTicket);
        }


    }



}
