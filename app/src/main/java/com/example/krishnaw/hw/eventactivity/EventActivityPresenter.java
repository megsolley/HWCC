package com.example.krishnaw.hw.eventactivity;

import com.example.krishnaw.hw.models.Event;
import com.example.krishnaw.hw.models.EventsData;
import com.example.krishnaw.hw.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by krishna.wadhwani on 10/20/17.
 */

class EventActivityPresenter implements IEventActivityPresenter, IEventActivityPresenter.onDataReceived{
    private final String clientId = "OTMxODI0MXwxNTA4MzkxMzA1Ljk";
    private IEventView eventView;
    private IEventActivityPresenter.onDataReceived listener;
    private String searchTerm ="";

    public EventActivityPresenter(IEventView eventView) {
        this.eventView = eventView;
        listener = this;
    }


    @Override
    public void performSearch(String search) {
        if(!this.searchTerm.equals(search)&& search.length()>=2) {
            this.searchTerm = search;
            Call<EventsData> call = ApiClient.seekGeekClient().getDataByQuery(clientId, searchTerm);
            call.enqueue(new Callback<EventsData>() {
                @Override
                public void onResponse(Call<EventsData> call, Response<EventsData> response) {
                    listener.onSuccess(response.body().getEvents());
                }

                @Override
                public void onFailure(Call<EventsData> call, Throwable t) {
                    listener.onFailure();
                }
            });
        }
    }

    @Override
    public void onSuccess(List<Event> eventList) {
        eventView.setEventList(eventList);
    }

    @Override
    public void onFailure() {

    }
}
