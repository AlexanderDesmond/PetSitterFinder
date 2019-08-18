package com.mad.assignment.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mad.assignment.R;
import com.mad.assignment.model.Listing;
import com.mad.assignment.model.Listings;
import com.mad.assignment.view.ViewListingActivity;

/**
 * Class for creating a new listing.
 */
public class CreateListingPresenter implements CreateListingContract.Presenter {

    private static final String LISTINGS = "listings";
    private static final String USER_ID = "USER_ID";
    private static final String LISTING_KEY = "LISTING_KEY";

    // context
    private final Context mContext;

    private String mUserId;
    private String mListingKey;

    private String mRequiredField;

    /**
     * Constructor
     */
    public CreateListingPresenter(Context context) {
        mContext = context;

        mRequiredField = mContext.getResources().getString(R.string.required_field);
    }

    /**
     * Handles start.
     */
    @Override
    public void start() {

    }

    /**
     * Creates a new listing.
     */
    @Override
    public void createListing(TextInputEditText descriptionTI, String availabilityFrom, String availabilityTo, CheckBox dogCB, CheckBox catCB, CheckBox guineaPigCB, CheckBox amphibianCB, CheckBox reptileCB, EditText othersET, EditText numberET) {

        // Validates
        if (!validate(descriptionTI, availabilityFrom, availabilityTo, numberET)) return;

        DatabaseReference listingsReference = FirebaseDatabase.getInstance().getReference().child(LISTINGS);

        mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Generate key for listing
        mListingKey = listingsReference.push().getKey();

        // Create new listing
        Listing listing = new Listing(mListingKey, mUserId, descriptionTI.getText().toString().trim(), availabilityFrom, availabilityTo, ifPet(dogCB), ifPet(catCB), ifPet(guineaPigCB), ifPet(amphibianCB), ifPet(reptileCB), othersET.getText().toString().trim(), numberET.getText().toString().trim());
        new Listings().add(listing);

        // Upload new listing to database
        listingsReference.child(mListingKey).setValue(listing);

        submitListing();
    }

    /**
     * Submits the listing and then opens it on a new activity.
     */
    public void submitListing() {
        Intent intent = new Intent(mContext, ViewListingActivity.class);
        intent.putExtra(USER_ID, mUserId);
        intent.putExtra(LISTING_KEY, mListingKey);
        mContext.startActivity(intent);
    }

    /**
     * Checks if all of the required fields are filled-in.
     */
    @Override
    public boolean validate(TextInputEditText descriptionTI, String dateFrom, String dateTo, EditText numberET) {
        boolean valid = true;

        if (TextUtils.isEmpty(descriptionTI.getText().toString())) {
            descriptionTI.setError(mRequiredField);
            valid = false;
        } else {
            descriptionTI.setError(null);
        }

        if (dateFrom == null || dateTo == null) {

            valid = false;
        }

        if (TextUtils.isEmpty(numberET.getText().toString())) {
            numberET.setError(mRequiredField);
            valid = false;
        } else {
            numberET.setError(null);
        }

        return valid;
    }

    /**
     * Checks if a checkbox is checked or not.
     */
    @Override
    public boolean ifPet(CheckBox checkBox) {
        boolean bool = false;

        if (checkBox.isChecked()) {
            bool = true;
        }

        return bool;
    }

    /**
     * Formats the date data.
     */
    @Override
    public String formatData(int day, int month, int year) {
        String date = "";

        // Have to increment month as they normally start at 0 (e.g. January is 0, February is 1, etc.).
        date = day + "/" + month++ + "/" + year;

        return date;
    }
}
