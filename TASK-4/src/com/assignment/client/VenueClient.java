package com.assignment.client;

import com.assignment.entity.Venue;

import java.util.Scanner;

public class VenueClient {
    public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
        Venue v=new Venue();
        System.out.print("enter the venue name:");
        String a=sc.next();
        v.setVenue_name(a);
        System.out.print("enter the venue address:");
        String b=sc.next();
        v.setAddress(b);

        v=new Venue(b,a);
        System.out.println("Venue Details:");
        v.display_venue_details();
    }
}
