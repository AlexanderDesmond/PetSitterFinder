package com.mad.assignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mad.assignment.R;
import com.mad.assignment.presenter.MyAccountPresenter;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class to handle the activity MyAccountActivity.
 */
public class MyAccountActivity extends AppCompatActivity {

    // Instantiates the views.
    @BindView(R.id.myAccount_userEmail_textView) TextView mUserEmailTextView;
    @BindView(R.id.myAccount_userFirstName_textView) TextView mUserFirstNameTextView;
    @BindView(R.id.myAccount_userLastName_textView) TextView mUserLastNameTextView;
    @BindView(R.id.myAccount_userPhoneNumber_textView) TextView mUserPhoneNumberTextView;
    @BindView(R.id.myAccount_userAddress_textView) TextView mUserAddressTextView;

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // Binds views.
        ButterKnife.bind(this);

        // Loads data from currently logged-in user's account.
        new MyAccountPresenter(getApplicationContext()).loadData(mUserEmailTextView, mUserFirstNameTextView, mUserLastNameTextView, mUserPhoneNumberTextView, mUserAddressTextView);

    }

    /**
     * Starts EditAccountActivity activity.
     */
    public void editAccount(View v) {
        startActivity(new Intent(this, EditAccountActivity.class));
    }
}
