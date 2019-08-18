package com.mad.assignment.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mad.assignment.R;
import com.mad.assignment.model.User;
import com.mad.assignment.model.Users;
import com.mad.assignment.view.HomeActivity;

/**
 * Class for registering a new account.
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    private static final String USERS = "users";
    private static final String ERROR = "ERROR";

    private final Context mContext;

    private DatabaseReference mUsersReference = FirebaseDatabase.getInstance().getReference().child(USERS);

    private String mNoMatch;
    private String mRequiredField;
    /**
     * Constructor
     */
    public RegisterPresenter(Context context) {
        mContext = context;

        mNoMatch = mContext.getResources().getString(R.string.passwords_do_not_match);
        mRequiredField = mContext.getResources().getString(R.string.required_field);
    }

    /**
     * Starts
     */
    @Override
    public void start() {

    }

    /**
     * Handles registration of new user.
     */
    @Override
    public void register(final String email, final String password, final String name, final String surname, final String phone, final String address) {
        final FirebaseAuth auth = FirebaseAuth.getInstance();

        // Check firebase auth for existing email
        auth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                if (task.isSuccessful()) {
                    ProviderQueryResult result = task.getResult();

                    if (result != null && result.getProviders() != null && result.getProviders().size() > 0) {
                        // There is already a user with that email address.
                        Log.d(ERROR, "There is already a user with this email address.");
                    } else {

                        // Create new user
                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(ERROR, "Registration successful.");

                                    // Login to newly created account.
                                    auth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        // Sign in success, update UI with the signed-in user's information
                                                        Log.d(ERROR, "Signing-in successful.");

                                                        // After successful login go to home page.
                                                        mContext.startActivity(new Intent(mContext, HomeActivity.class));

                                                    } else {
                                                        // If sign in fails, display an error message.

                                                        Log.d(ERROR, "Signing-in failed.");
                                                    }
                                                }
                                            });

                                    // Write new user to database.
                                    saveUser(email, name, surname, phone, address);
                                } else {
                                    Toast.makeText(mContext,
                                            "registration failed.",
                                            Toast.LENGTH_SHORT).show();
                                    Log.d(ERROR, "Writing new user to database failed.");
                                }
                            }
                        });
                    }
                } else {
                    // Wasn't successful for some reason.
                    Toast.makeText(mContext,
                            "There is a problem, please try again later.",
                            Toast.LENGTH_SHORT).show();
                    Log.d(ERROR, "Process failed. Please try again.");
                }
            }
        });
    }

    /**
     * Compares passwords to ensure a match.
     */
    @Override
    public boolean comparePasswords(EditText passwordET, EditText confirmET) {
        boolean match = true;

        if (!passwordET.getText().toString().equals(confirmET.getText().toString())) {
            confirmET.setError(mNoMatch);
            match = false;
        } else {
            confirmET.setError(null);

        }

        return match;
    }

    /**
     * Validates form to ensure all fields are filled-in.
     */
    @Override
    public boolean validate(EditText emailET, EditText passwordET, EditText confirmET, EditText nameET, EditText surnameET, EditText phoneET, EditText addressET) {
        boolean valid = true;

        if (TextUtils.isEmpty(emailET.getText().toString())) {
            emailET.setError(mRequiredField);
            valid = false;
        } else {
            emailET.setError(null);
        }

        if (TextUtils.isEmpty(passwordET.getText().toString())) {
            passwordET.setError(mRequiredField);
            valid = false;
        } else {
            passwordET.setError(null);
        }

        if (TextUtils.isEmpty(confirmET.getText().toString())) {
            confirmET.setError(mRequiredField);
            valid = false;
        } else {
            confirmET.setError(null);
        }

        if (TextUtils.isEmpty(nameET.getText().toString())) {
            nameET.setError(mRequiredField);
            valid = false;
        } else {
            nameET.setError(null);
        }

        if (TextUtils.isEmpty(surnameET.getText().toString())) {
            surnameET.setError(mRequiredField);
            valid = false;
        } else {
            surnameET.setError(null);
        }

        if (TextUtils.isEmpty(phoneET.getText().toString())) {
            phoneET.setError(mRequiredField);
            valid = false;
        } else {
            phoneET.setError(null);
        }

        if (TextUtils.isEmpty(addressET.getText().toString())) {
            addressET.setError(mRequiredField);
            valid = false;
        } else {
            addressET.setError(null);
        }

        return valid;
    }

    /**
     * Saves new user to database.
     */
    @Override
    public void saveUser(String emailAddress, String firstName, String lastName, String phoneNumber, String address) {
        // Create new user object
        User user = new User(emailAddress, firstName, lastName, phoneNumber, address);
        new Users().addUser(user);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Save new user object to database
        mUsersReference.child(userId).setValue(user);
    }
}
