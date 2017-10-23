package com.example.krishnaw.hw.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Created by krishna.wadhwani on 10/20/17.
 */

public class Venue implements Parcelable
{
    @SerializedName("display_location")
    private String displayLocation;

    public String getDisplayLocation() {
        return displayLocation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.displayLocation);
    }

    public static final Parcelable.Creator<Venue> CREATOR
            = new Parcelable.Creator<Venue>() {
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

    private Venue(Parcel in){
        this.displayLocation = in.readString();
    }
}
