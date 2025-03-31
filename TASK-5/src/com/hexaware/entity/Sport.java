package com.hexaware.entity;

public class Sport extends Event{
    private String sportName;
    private String teamsName;

    public Sport() {
    }

    public Sport(String sportName, String teamsName) {
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getTeamsName() {
        return teamsName;
    }

    public void setTeamsName(String teamsName) {
        this.teamsName = teamsName;
    }
    public void display_sport_details(){
        System.out.println("Sport name:"+getSportName());
        System.out.println("Teams name"+getTeamsName());
    }
}
