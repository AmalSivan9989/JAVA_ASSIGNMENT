package com.assignment.client;
import com.assignment.entity.Event;

import java.util.Scanner;



public class EventClient {
    public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
        Event en = new Event();
        System.out.print("enter the event name:");
        String a=sc.next();
               en.setEvent_name(a);
        System.out.print("enter the event date:");
        String b=sc.next();
                en.setEvent_date(b);
        System.out.print("enter the event time:");
        String c=sc.next();
                en.setEvent_time(c);
        System.out.print("enter the venue name:");
        String d=sc.next();
                en.setVenue_name(d);
        System.out.print("enter the total seats:");
        int e=sc.nextInt();
                en.setTotal_seats(e);
        System.out.print("enter the available seats:");
        int f=sc.nextInt();
                en.setAvailable_seats(f);
        System.out.print("enter the ticket price:");
        double g=sc.nextDouble();
                en.setTicket_price(g);
        System.out.print("enter the event type:");
        String h=sc.next();
                en.setEvent_type(h);

        en=new Event(f,b,a,h,g,e,d,c);

        System.out.println("\nEvent Details:");
        en.display_event_details();


        Event.eventMethods(en);






    }
}
