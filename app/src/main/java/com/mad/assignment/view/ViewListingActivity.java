package com.mad.assignment.view;

import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.mad.assignment.R;
import com.mad.assignment.presenter.ViewListingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class to handle the activity ViewListingActivity.
 */
public class ViewListingActivity extends AppCompatActivity {

    private static final String USER_ID = "USER_ID";
    private static final String LISTING_KEY = "LISTING_KEY";
    private static final String PETS = "PETS";


    // Instantiates the views.
    @BindView(R.id.listing_name_textView) TextView mNameTextView;
    @BindView(R.id.listing_address_textView) TextView mAddressTextView;
    @BindView(R.id.listing_phone_textView) TextView mPhoneTextView;
    @BindView(R.id.listing_availability_textView) TextView mAvailabilityTextView;
    @BindView(R.id.listing_number_textView) TextView mNumberTextView;
    @BindView(R.id.listing_pets_textView) TextView mPetsTextView;
    @BindView(R.id.email_fab) FloatingActionButton emailFab;
    @BindView(R.id.message_fab) FloatingActionButton messageFab;

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listing);

        // Binds views.
        ButterKnife.bind(this);

        // Pulls data from intents
        String listingPoster = getIntent().getStringExtra(USER_ID);
        String listingKey = getIntent().getStringExtra(LISTING_KEY);
        String pets = getIntent().getStringExtra(PETS);

        mPetsTextView.setText(pets);

        final ViewListingPresenter presenter = new ViewListingPresenter(getApplicationContext());

        // Load relevant data from the listing and its poster.
        presenter.getUserData(mNameTextView, mAddressTextView, mPhoneTextView, listingPoster);
        presenter.getListingData(mAvailabilityTextView, mNumberTextView, listingKey);

        /**
         * Handles setOnClickListener emailFab floating action button.
         */
        emailFab.setOnClickListener(new View.OnClickListener() {
            /**
             * Opens email app through intent when clicked.
             */
            @Override
            public void onClick(View view) {
                presenter.sendEmail(presenter.getEmail());
            }
        });

        /**
         * Handles setOnClickListener messageFab floating action button.
         */
        messageFab.setOnClickListener(new View.OnClickListener() {
            /**
             * Opens messaging app through intent when clicked.
             */
            @Override
            public void onClick(View view) {
                presenter.sendMessage(presenter.getNumber());
            }
        });
    }
}
