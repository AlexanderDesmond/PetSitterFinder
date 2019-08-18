package com.mad.assignment.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mad.assignment.R;
import com.mad.assignment.presenter.EditAccountPresenter;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class to handle the activity EditAccountActivity.
 */
public class EditAccountActivity extends AppCompatActivity {

    private static final String USERS = "users";

    // Instantiates view.
    @BindView(R.id.editAccount_firstName_editText) EditText mFirstNameEditText;
    @BindView(R.id.editAccount_lastName_editText) EditText mLastNameEditText;
    @BindView(R.id.editAccount_password_editText) EditText mPasswordEditText;
    @BindView(R.id.editAccount_confirmPassword_editText) EditText mConfirmPasswordEditText;
    @BindView(R.id.editAccount_phoneNumber_editText) EditText mPhoneEditText;
    @BindView(R.id.editAccount_address_editText) EditText mAddressEditText;

    // Instantiates string resources.
    @BindString(R.string.required_field) String mRequiredField;
    @BindString(R.string.passwords_do_not_match) String mNoMatch;

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        // Binds views.
        ButterKnife.bind(this);
    }

    /**
     * Handles the editing of account information when the save button is clicked.
     */
    public void save(View v) {
        // Edits account information.
        new EditAccountPresenter(getApplicationContext()).editAccountDetails(mFirstNameEditText, mLastNameEditText, mPasswordEditText, mConfirmPasswordEditText, mPhoneEditText, mAddressEditText);
    }
}
