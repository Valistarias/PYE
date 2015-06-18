package com.example.valistar.pye.model;

/**
 * Created by Valistar on 18/06/2015.
 */
public class Event {

    public String eventId;
    public String name;
    public String description;

    public Event(String eventId, String name, String description) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
    }

    public Event(){
        this.eventId = "";
        this.name = "";
        this.description = "";
    }

    public void setEventId(String eventId){
        this.eventId = eventId;
    }

    public String getEventId(){
        return eventId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}

