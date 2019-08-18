package com.mad.assignment.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mad.assignment.R;
import com.mad.assignment.model.User;
import com.mad.assignment.view.HomeActivity;

/**
 * Class for editing/updating account details.
 */
public class EditAccountPresenter implements EditAccountContract.Presenter {

    private static final String USERS = "users";

    private final Context mContext;

    /**
     * Constructor
     */
    public EditAccountPresenter(Context context) {
        mContext = context;
    }

    /**
     * Handles start
     */
    @Override
    public void start() {

    }

    /**
     * Attempts to update account details.
     */
    @Override
    public void editAccountDetails(EditText nameET, EditText surnameET, EditText passwordET, EditText confirmPasswordET, EditText phoneET, EditText addressET) {
        // Check if the passwords match.
        if (!comparePasswords(passwordET, confirmPasswordET)) return;
        // Check if all fields are filled-in.
        if (!validate(nameET, surnameET, passwordET, confirmPasswordET, phoneET, addressET)) return;

        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child(USERS).child(FirebaseAuth.getInstance().getUid());

        // Create new user object
        User user = new User(FirebaseAuth.getInstance().getCurrentUser().getEmail(), nameET.getText().toString().trim(), surnameET.getText().toString().trim(), phoneET.getText().toString().trim(), addressET.getText().toString().trim());
        // Update user in database with new user object
        userReference.setValue(user);

        // Update password
        updatePassword(passwordET.getText().toString().trim());

        // Direct user back to home screen in app
        mContext.startActivity(new Intent(mContext, HomeActivity.class));

    }

    /**
     * Updates user's firebase authentication password.
     */
    private void updatePassword(String password) {

        FirebaseAuth.getInstance().getCurrentUser().updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Password change successful
                        }
                    }
                });
    }

    /**
     * Compares the two passwords to ensure a match.
     */
    private boolean comparePasswords(EditText passwordET, EditText confirmPasswordET) {
        boolean match = true;

        if (!passwordET.getText().toString().trim().equals(confirmPasswordET.getText().toString().trim())) {
            confirmPasswordET.setError(mContext.getResources().getString(R.string.passwords_do_not_match));
            match = false;
        } else {
            confirmPasswordET.setError(null);
        }

        return match;
    }

    /**
     * Checks if all the required fields are filled-in.
     */
    private boolean validate(EditText nameET, EditText surnameET, EditText passwordET, EditText confirmPasswordET, EditText phoneET, EditText addressET) {
        boolean valid = true;

        String requiredField = mContext.getResources().getString(R.string.required_field);

        if (TextUtils.isEmpty(nameET.getText().toString())) {
            nameET.setError(requiredField);
            valid = false;
        } else {
            nameET.setError(null);
        }

        if (TextUtils.isEmpty(surnameET.getText().toString())) {
            surnameET.setError(requiredField);
            valid = false;
        } else {
            surnameET.setError(null);
        }

        if (TextUtils.isEmpty(passwordET.getText().toString())) {
            passwordET.setError(requiredField);
            valid = false;
        } else {
            passwordET.setError(null);
        }

        if (TextUtils.isEmpty(confirmPasswordET.getText().toString())) {
            confirmPasswordET.setError(requiredField);
            valid = false;
        } else {
            confirmPasswordET.setError(null);
        }

        if (TextUtils.isEmpty(phoneET.getText().toString())) {
            phoneET.setError(requiredField);
            valid = false;
        } else {
            phoneET.setError(null);
        }

        if (TextUtils.isEmpty(addressET.getText().toString())) {
            addressET.setError(requiredField);
            valid = false;
        } else {
            addressET.setError(null);
        }

        return valid;
    }
}
