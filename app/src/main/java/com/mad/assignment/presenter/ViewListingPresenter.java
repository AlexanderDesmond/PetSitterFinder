package com.mad.assignment.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.assignment.R;

/**
 * Displays a user's listing.
 */
public class ViewListingPresenter implements ViewListingContract.Presenter {

    private static final String USERS = "users";
    private static final String LISTINGS = "listings";

    private static final String EMAIL = "emailAddress";
    private static final String FIRST_NAME = "firstName";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phoneNumber";

    private static final String AVAILABILITY_FROM = "availabilityFrom";
    private static final String AVAILABILITY_TO = "availabilityTo";
    private static final String PETS = "numberOfAnimals";

    // Context
    private final Context mContext;

    private final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    private String mEmail;
    private String mNumber;

    /**
     * Constructor
     */
    public ViewListingPresenter(Context context) {
        mContext = context;
    }

    /**
     * Gets user data from database from display.
     */
    @Override
    public void getUserData(final TextView nameTV, final TextView addressTV, final TextView phoneTV, String listingPoster) {
        DatabaseReference userReference = mFirebaseDatabase.getReference().child(USERS).child(listingPoster);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mEmail = dataSnapshot.child(EMAIL).getValue().toString();
                nameTV.setText(dataSnapshot.child(FIRST_NAME).getValue().toString());
                addressTV.setText(dataSnapshot.child(ADDRESS).getValue().toString());
                mNumber = dataSnapshot.child(PHONE_NUMBER).getValue().toString();
                phoneTV.setText(mNumber);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Gets listing data from database for display
     */
    @Override
    public void getListingData(final TextView availabilityTV, final TextView numberTV, String listingKey) {
        DatabaseReference listingReference = mFirebaseDatabase.getReference().child(LISTINGS).child(listingKey);

        listingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                availabilityTV.setText(mContext.getString(R.string.availability_period, dataSnapshot.child(AVAILABILITY_FROM).getValue().toString(), dataSnapshot.child(AVAILABILITY_TO).getValue().toString()));
                numberTV.setText(mContext.getString(R.string.takes_pets, dataSnapshot.child(PETS).getValue().toString()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Handles sending of email to listing poster.
     */
    @Override
    public void sendEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.email_subject));

        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    /**
     * Handles sending of message to listing poster.
     */
    @Override
    public void sendMessage(String number) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + number));

        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    /**
     * Returns email address of listing poster.
     */
    @Override
    public String getEmail() {
        return mEmail;
    }

    /**
     * Returns phone number of listing poster.
     */
    @Override
    public String getNumber() {
        return mNumber;
    }

    /**
     * Starts
     */
    @Override
    public void start() {

    }
}
