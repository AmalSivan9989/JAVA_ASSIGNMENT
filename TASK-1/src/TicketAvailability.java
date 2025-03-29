import java.util.*;
public class TicketAvailability {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the total Available Tickets:");
        int availableTicket=sc.nextInt();
        System.out.print("Enter the no of Tickets need to Book:");
        int noOfBookingTicket=sc.nextInt();
        if(ticketCheck(availableTicket,noOfBookingTicket)){
            System.out.println("Tickets are available!. Please continue booking");

        }
        else{
            System.out.println("Tickets are not available!");
            System.out.println("Tickets available are:"+availableTicket);
        }



    }
    public static boolean ticketCheck(int availableTicket,int noOfBookingTicket){
        if(noOfBookingTicket>availableTicket){
            return false;
        }
        else{
            return true;
        }
    }
}
