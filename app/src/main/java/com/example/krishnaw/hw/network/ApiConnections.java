package com.example.krishnaw.hw.network;

import com.example.krishnaw.hw.models.EventsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Created by krishna.wadhwani on 10/19/17.
 */

public interface ApiConnections {

    @GET("2/events")
    Call<EventsData> getDataByQuery(
            @Query("client_id") String clientId,
            @Query("q") String query);
}
