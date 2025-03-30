package co.assignment.entity;

public class TicketAvailability {
    public static boolean ticketCheck(int availableTicket,int noOfBookingTicket){
        if(noOfBookingTicket>availableTicket){
            return false;
        }
        else{
            return true;
        }
    }
}
