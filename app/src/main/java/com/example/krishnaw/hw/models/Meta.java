package com.example.krishnaw.hw.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Created by krishna.wadhwani on 10/20/17.
 */

public class Meta {
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("total")
    private int total;
    @SerializedName("page")
    private int page;

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }
}
