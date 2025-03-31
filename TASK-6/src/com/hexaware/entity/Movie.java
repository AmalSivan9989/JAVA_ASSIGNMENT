package com.hexaware.entity;

public class Movie extends Event{
    private String genre;
    private String ActorName;
    private String ActresName;


    public Movie(String eventName, String eventDate, String eventTime, int totalSeats, double ticketPrice, String venueName, String actorName, String actressName, String genre) {
        super(eventName, eventDate, eventTime, totalSeats, ticketPrice, venueName);
        this.ActorName = actorName;
        this.ActresName = actressName;
        this.genre = genre;
    }


    public String getActorName() {
        return ActorName;
    }

    public void setActorName(String actorName) {
        ActorName = actorName;
    }

    public String getActresName() {
        return ActresName;
    }

    public void setActresName(String actresName) {
        ActresName = actresName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void displayEventDetails() {
        System.out.println("Movie Event: " + eventName + " featuring " + actorName + " and " + actressName + " (Genre: " + genre + ") at " + venueName + " on " + eventDate + " " + eventTime);
    }
}
