package com.mad.assignment.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mad.assignment.R;
import com.mad.assignment.presenter.RegisterPresenter;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class to handle the activity RegisterActivity.
 */
public class RegisterActivity extends AppCompatActivity {

    // Instantiates the views.
    @BindView(R.id.register_email_editText) EditText mEmailEditText;
    @BindView(R.id.register_password_editText) EditText mPasswordEditText;
    @BindView(R.id.register_confirmPassword_editText) EditText mConfirmPasswordEditText;
    @BindView(R.id.register_firstName_editText) EditText mFirstNameEditText;
    @BindView(R.id.register_lastName_editText) EditText mLastNameEditText;
    @BindView(R.id.register_phone_editText) EditText mPhoneEditText;
    @BindView(R.id.register_address_editText) EditText mAddressEditText;

    // Instantiates string resources.
    @BindString(R.string.required_field) String mRequiredField;
    @BindString(R.string.passwords_do_not_match) String mNoMatch;

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Binds views.
        ButterKnife.bind(this);
    }

    /**
     * Compares the entered passwords and validates the form before registering a the new user.
     */
    public void register(View v) {
        // Ensures the passwords match.
        if (!new RegisterPresenter(getApplicationContext()).comparePasswords(mPasswordEditText, mConfirmPasswordEditText)) return;
        // Ensures the form is fully filled out.
        if (!new RegisterPresenter(getApplicationContext()).validate(mEmailEditText, mPasswordEditText, mConfirmPasswordEditText, mFirstNameEditText, mLastNameEditText, mPhoneEditText, mAddressEditText)) return;

        // Registers the new user.
        new RegisterPresenter(getApplicationContext()).register(mEmailEditText.getText().toString().trim(), mPasswordEditText.getText().toString().trim(),
                mFirstNameEditText.getText().toString().trim(), mLastNameEditText.getText().toString().trim(), mPhoneEditText.getText().toString().trim(), mAddressEditText.getText().toString().trim());
    }
}
