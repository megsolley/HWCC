package com.example.krishnaw.hw.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by krishna.wadhwani on 10/21/17.
 */

public class SharedPreferencesImpl implements ISharedPreferences {
    private static final String FAVORITES_KEY = "favorites_key";
    private SharedPreferences favPrefs;

    public SharedPreferencesImpl(Context context) {
        favPrefs = context.getSharedPreferences(FAVORITES_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public Set<String> getFavorites() {
        return getFavorites(FAVORITES_KEY);
    }

    @Override
    public void saveFavorites(String eventId) {
        this.saveFavorites(getFavorites(), eventId);
    }

    private Set<String> getFavorites(String key) {
        return favPrefs.getStringSet(key, new HashSet<String>());
    }

    private void saveFavorites(Set<String> favSet, String eventId) {
        if (favSet != null) {
            System.out.println(new Gson().toJson(favSet));
            if (favSet.contains(eventId)) {
                favSet.remove(eventId);
            } else {
                favSet.add(eventId);
            }
            SharedPreferences.Editor prefsEditor = favPrefs.edit().clear();
            prefsEditor.putStringSet(FAVORITES_KEY, favSet);
            prefsEditor.apply();
        }
    }
}
