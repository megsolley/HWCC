package com.example.krishnaw.hw.sharedpreferences;

import java.util.Set;

/**
 *
 * Created by krishna.wadhwani on 10/21/17.
 */

public interface ISharedPreferences {

    Set<String> getFavorites();

    void saveFavorites(String eventId);


}
