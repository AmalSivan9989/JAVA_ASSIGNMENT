package com.hexaware.entity;

public class Movie extends Event {
    private String genre;
    private String ActorName;
    private String ActresName;

    public Movie() {
    }

    public Movie(String actorName, String actresName, String genre) {
        ActorName = actorName;
        ActresName = actresName;
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
    public void display_event_details(){
        System.out.println("Actor name:"+getActorName());
        System.out.println("Actress name:"+getActresName());
        System.out.println("Genre:"+getGenre());
    }

}
