package com.example.krishnaw.hw.eventactivity;

import com.example.krishnaw.hw.models.Event;

import java.util.List;

/**
 *
 * Created by krishna.wadhwani on 10/20/17.
 */

public interface IEventActivityPresenter {
    void performSearch(String searchTerm);

    interface onDataReceived{
        void onSuccess(List<Event> eventList);
        void onFailure();
    }

    interface recyclerViewItemClick{
        void eventItemClick(Event event);
    }
}
