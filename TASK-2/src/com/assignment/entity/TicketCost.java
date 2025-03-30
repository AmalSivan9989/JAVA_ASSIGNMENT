package com.assignment.entity;

public class TicketCost {
    private static double price;
    static double totalPrice;

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
}
