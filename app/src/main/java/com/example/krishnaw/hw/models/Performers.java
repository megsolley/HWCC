package com.example.krishnaw.hw.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by krishna.wadhwani on 10/21/17.
 */

public class Performers implements Parcelable {

    private String image;

    //Use this default image if image Url in resposne is null
    public final static String defautImageUrl = "https://picsum.photos/300?image=855";

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
    }

    public static final Parcelable.Creator<Performers> CREATOR
            = new Parcelable.Creator<Performers>() {
        public Performers createFromParcel(Parcel in) {
            return new Performers(in);
        }

        public Performers[] newArray(int size) {
            return new Performers[size];
        }
    };

    private Performers(Parcel in) {
        image = in.readString();
    }
}
