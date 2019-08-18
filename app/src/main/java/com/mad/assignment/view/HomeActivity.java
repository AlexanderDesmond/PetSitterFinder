package com.mad.assignment.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.mad.assignment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class to handle the activity HomeActivity.
 */
public class HomeActivity extends AppCompatActivity {

    // Instantiates view.
    @BindView(R.id.home_account_TextView) TextView mAccountTextView;

    // Firebase authentication object
    private FirebaseAuth mAuth;

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Binds views.
        ButterKnife.bind(this);

        // Instantiattes firebase object.
        mAuth = FirebaseAuth.getInstance();

    }

    /**
     * Handles the resuming of the activity.
     */
    @Override
    protected void onResume() {
        super.onResume();

        mAccountTextView.setText(mAuth.getCurrentUser().getEmail());
    }

    /**
     * Handles the creation of the options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    /**
     * Handles the clicking of an item in the options menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_account:
                myAccount();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Starts MyAccount activity.
     */
    private void myAccount() {
        startActivity(new Intent(this, MyAccountActivity.class));
    }

    /**
     * Starts ViewListings activity.
     */
    public void findPetCare(View v) {
        startActivity(new Intent(this, ViewListingsActivity.class));
    }

    /**
     * Starts CreateListing activity.
     */
    public void offerPetCare(View v) {
        startActivity(new Intent(this, CreateListingActivity.class));
    }
}
