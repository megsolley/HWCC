package com.example.krishnaw.hw.eventactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.krishnaw.hw.R;
import com.example.krishnaw.hw.adapters.EventsListAdapter;
import com.example.krishnaw.hw.databinding.ActivityMainBinding;
import com.example.krishnaw.hw.detailsactivity.DetailsActivity;
import com.example.krishnaw.hw.models.Event;
import com.example.krishnaw.hw.sharedpreferences.ISharedPreferences;
import com.example.krishnaw.hw.sharedpreferences.SharedPreferencesImpl;
import com.google.gson.Gson;

import java.util.List;
import java.util.Set;

public class EventActivity extends AppCompatActivity implements IEventView {

    private ActivityMainBinding binding;
    private EventActivityPresenter mainActivityPresenter;
    private ISharedPreferences sharedPreferences;
    private Set<String> favorites;
    private EventsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = new SharedPreferencesImpl(this);
        favorites = sharedPreferences.getFavorites();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityPresenter = new EventActivityPresenter(this);
        this.binding.searchText.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mainActivityPresenter.performSearch(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void setEventList(List<Event> eventList) {
        LinearLayoutManager mEventsLayoutManager;
        System.out.println(new Gson().toJson(eventList));
        mAdapter = new EventsListAdapter(eventList, this, favorites);
        mEventsLayoutManager = new LinearLayoutManager(this);
        this.binding.geekList.setVisibility(View.VISIBLE);
        this.binding.geekList.setLayoutManager(mEventsLayoutManager);
        this.binding.geekList.setAdapter(mAdapter);
    }

    @Override
    public void onEventClick(Event event) {
        Intent intent = new Intent(EventActivity.this, DetailsActivity.class);
        intent.putExtra(getString(R.string.event), event);
        intent.putExtra(getString(R.string.is_favorite), favorites.contains(event.getId().toString()));

        startActivityForResult(intent, Event.eventFav);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Event.eventFav) {
            if (resultCode == RESULT_OK) {
                this.notifyFavoriteChangesToAdaper();
            }
        }
    }

    private void notifyFavoriteChangesToAdaper(){
        this.favorites.clear();
        this.favorites.addAll(sharedPreferences.getFavorites());
        mAdapter.notifyDataSetChanged();
    }
}
