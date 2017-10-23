package com.example.krishnaw.hw.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by krishna.wadhwani on 10/18/17.
 */

public class ApiClient {
    private static ApiConnections SEEK_GEEK_CLIENT;
    private static final String API_BASE_URL = "https://api.seatgeek.com/";

    static {
        createClient();
    }

    public static ApiConnections seekGeekClient() {
        return SEEK_GEEK_CLIENT;
    }

    private static void createClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SEEK_GEEK_CLIENT = retrofit.create(ApiConnections.class);

    }
}
