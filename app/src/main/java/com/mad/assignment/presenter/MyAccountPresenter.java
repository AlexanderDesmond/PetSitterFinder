package com.mad.assignment.presenter;

import android.content.Context;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Loads user's account information.
 */
public class MyAccountPresenter implements MyAccountContract.Presenter {

    private static final String USERS = "users";

    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String ADDRESS = "address";

    // Context
    private final Context mContext;

    /**
     * Constructor
     */
    public MyAccountPresenter(Context context) {
        mContext = context;
    }

    /**
     * Loads data for display on screen.
     */
    @Override
    public void loadData(final TextView email, final TextView name, final TextView surname, final TextView phone, final TextView address) {

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference().child(USERS).child(id);

        /**
         * Checks if there is a change in the database at the reference.
         */
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Updates fields in view with data from firebase
                email.setText(dataSnapshot.child(EMAIL_ADDRESS).getValue().toString());
                name.setText(dataSnapshot.child(FIRST_NAME).getValue().toString());
                surname.setText(dataSnapshot.child(LAST_NAME).getValue().toString());
                phone.setText(dataSnapshot.child(PHONE_NUMBER).getValue().toString());
                address.setText(dataSnapshot.child(ADDRESS).getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Handles start
     */
    @Override
    public void start() {

    }
}
