package com.example.krishnaw.hw.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by krishna.wadhwani on 10/20/17.
 */

public class Event implements Parcelable {
    @SerializedName("id")
    private Long id;
    @SerializedName("title")
    private String title;
    @SerializedName("venue")
    private Venue venue;
    @SerializedName("datetime_local")
    private String dateTime;
    @SerializedName("performers")
    private List<Performers> performers;
    @SerializedName("short_title")
    String shortTitle;

    private String customDate;

    public String getCustomDate() {
        return formatDate();
    }

    public List<Performers> getPerformers() {
        return performers;
    }

    public static final int eventFav = 101;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.shortTitle);
        parcel.writeParcelable(this.venue, i);
        parcel.writeString(this.dateTime);
        parcel.writeList(this.performers);
        parcel.writeString(this.customDate);

    }

    public static final Parcelable.Creator<Event> CREATOR
            = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    private Event(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.shortTitle = in.readString();
        this.venue = in.readParcelable(getClass().getClassLoader());
        this.dateTime = in.readString();
        this.performers = new ArrayList<>();
        in.readList(this.performers, getClass().getClassLoader());
        this.customDate = in.readString();

    }

    private String formatDate() {
        DateTime dateTime = DateTime.parse(this.dateTime);
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendDayOfWeekShortText().appendLiteral(',')
                .appendLiteral(' ')
                .appendDayOfMonth(2)
                .appendLiteral(' ')
                .appendMonthOfYearShortText()
                .appendLiteral(' ').appendYear(4, 4)
                .appendLiteral(' ')
                .appendHourOfHalfday(2)
                .appendLiteral(':')
                .appendMinuteOfHour(2)
                .appendLiteral(' ')
                .appendHalfdayOfDayText()
                .toFormatter();
        customDate = fmt.print(dateTime);
        return customDate;
    }
}

