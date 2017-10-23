package com.example.krishnaw.hw.models;

import android.databinding.BaseObservable;

import java.util.Objects;

/**
 *
 * Created by krishna.wadhwani on 10/19/17.
 */

public class SearchText extends BaseObservable {
    private String text;
    public String get() {
        return text != null ? text : "";
    }
    public void set(String value) {
        if (!Objects.equals(this.text, value)) {
            this.text = value;
            notifyChange();
        }
    }
    public boolean isEmpty() {
        return text == null || text.isEmpty();
    }


}
