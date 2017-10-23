package com.example.krishnaw.hw.detailsactivity;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.krishnaw.hw.R;
import com.example.krishnaw.hw.databinding.ActivityDetailsBinding;
import com.example.krishnaw.hw.models.Event;
import com.example.krishnaw.hw.models.Performers;
import com.example.krishnaw.hw.sharedpreferences.ISharedPreferences;
import com.example.krishnaw.hw.sharedpreferences.SharedPreferencesImpl;

public class DetailsActivity extends AppCompatActivity {

    private boolean isFavorite;
    private ISharedPreferences sharedPreferences;
    private Event event;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.is_favorite), isFavorite);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        setSupportActionBar(binding.toolbar);
        sharedPreferences = new SharedPreferencesImpl(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent receivedIntent = getIntent();
        if (receivedIntent.getExtras() != null) {
            this.event = receivedIntent.getExtras().getParcelable(getString(R.string.event));
            this.isFavorite = receivedIntent.getBooleanExtra(getString(R.string.is_favorite), false);
            binding.setEvent(event);
            binding.details.setEvent(event);
            if (event != null && event.getPerformers() != null && event.getPerformers().size() > 0)
                binding.details.setPerformers(event.getPerformers().get(0));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details_activity, menu);
        setFavoriteIcon(isFavorite, menu.findItem(R.id.action_add_fav));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_fav: {
                sharedPreferences.saveFavorites(this.event.getId().toString());
                isFavorite = !isFavorite;
                this.setFavoriteIcon(isFavorite, item);
                break;
            }
            case android.R.id.home: {
                this.onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter("bind:image")
    public static void loadImage(ImageView imageView, String v) {
        if (v != null)
            Glide.with(imageView.getContext()).load(v).into(imageView);
        else // load any image placeholder if received image url is null
            Glide.with(imageView.getContext()).load(Performers.defautImageUrl)
                    .into(imageView);
    }

    private void setFavoriteIcon(boolean isFavorite, MenuItem menuItem) {
        if (isFavorite) menuItem.setIcon(R.drawable.ic_favorite_white_24dp);
        else menuItem.setIcon(R.drawable.ic_favorite_border_white_24dp);
    }

}
