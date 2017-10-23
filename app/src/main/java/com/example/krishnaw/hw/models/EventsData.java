package com.example.krishnaw.hw.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Created by krishna.wadhwani on 10/20/17.
 */

public class EventsData {
    @SerializedName("events")
    private List<Event> events;

    @SerializedName("meta")
    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public List<Event> getEvents() {
        return events;
    }
}
