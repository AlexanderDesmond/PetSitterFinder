package com.mad.assignment.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mad.assignment.R;
import com.mad.assignment.presenter.ViewListingsPresenter;

/**
 * Class to handle the activity ViewListingsActivity.
 */
public class ViewListingsActivity extends AppCompatActivity {

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listings);

        // Loads recycler view list of listings.
        new ViewListingsPresenter(getApplicationContext(), (RecyclerView) findViewById(R.id.recycler_view)).createList();
    }
}
