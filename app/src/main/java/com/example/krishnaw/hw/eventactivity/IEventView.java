package com.example.krishnaw.hw.eventactivity;

import com.example.krishnaw.hw.models.Event;

import java.util.List;

/**
 *
 * Created by krishna.wadhwani on 10/21/17.
 */

public interface IEventView {
    void setEventList(List<Event> eventList);
    void onEventClick(Event event);
}
