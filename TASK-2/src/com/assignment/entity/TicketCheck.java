package com.assignment.entity;

public class TicketCheck {
    public static boolean ticketCheck(int availableTicket,int noOfBookingTicket){
        if(noOfBookingTicket>availableTicket){
            return false;
        }
        else{
            return true;
        }
    }
}
