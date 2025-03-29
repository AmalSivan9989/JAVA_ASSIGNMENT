import java.sql.SQLOutput;
import java.util.Scanner;

public class TicketCost {
    private static double price;
    static double totalPrice;
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

    public static double costOfTickets(String Class,int noOfTickets){



       switch(Class){
           case "Silver":
               price=200;
               totalPrice=price*noOfTickets;
               return totalPrice;
           case "Gold":
               price=300;
               totalPrice=price*noOfTickets;
               return totalPrice;
           case "Diamond":
               price=500;
               totalPrice=price*noOfTickets;
               return totalPrice;

           default:
               System.out.println("PLEASE! Enter the Correct Class");
               return 0;


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
