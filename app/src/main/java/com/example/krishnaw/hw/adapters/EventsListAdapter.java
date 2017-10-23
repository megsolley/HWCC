package com.example.krishnaw.hw.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.krishnaw.hw.R;
import com.example.krishnaw.hw.databinding.EventsListItemBinding;
import com.example.krishnaw.hw.eventactivity.IEventView;
import com.example.krishnaw.hw.eventactivity.IEventActivityPresenter;
import com.example.krishnaw.hw.models.Event;
import com.example.krishnaw.hw.models.Performers;

import java.util.List;
import java.util.Set;


/**
 *
 * Created by krishna.wadhwani on 10/20/17.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventViewHolder>
        implements IEventActivityPresenter.recyclerViewItemClick {

    private List<Event> eventList;
    private IEventView eventView;
    private Set<String > favoritesSet;


    public EventsListAdapter(List<Event> eventList, IEventView eventView, Set<String> favoritesSet) {
        this.eventList = eventList;
        this.eventView = eventView;
        this.favoritesSet = favoritesSet;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        EventsListItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.events_list_item, parent, false);
        return new EventViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        String id = eventList.get(position).getId().toString();
        holder.binding.setEvent(eventList.get(position));
        holder.binding.setFavorite(favoritesSet.contains(id));
        System.out.println(eventList.get(position).getDateTime());
        if (eventList.get(position).getPerformers() != null &&
                eventList.get(position).getPerformers().size() > 0)
            holder.binding.setPerformers(eventList.get(position).getPerformers().get(0));
        holder.binding.setClickListener(this);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void eventItemClick(Event event) {
        eventView.onEventClick(event);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        if (v != null)
            Glide.with(imageView.getContext()).load(v).apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        else // load image placeholder if received image url is null
            Glide.with(imageView.getContext()).load(Performers.defautImageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
    }

    @BindingAdapter({"bind:visible"})
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        EventsListItemBinding binding;

        EventViewHolder(EventsListItemBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }

        EventViewHolder(View itemView) {
            super(itemView);
        }
    }
}
