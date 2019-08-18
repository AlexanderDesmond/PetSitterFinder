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
import com.mad.assignment.R;
import com.mad.assignment.view.HomeActivity;

/**
 * Class for logging-in to account
 */
public class LoginPresenter implements LoginContract.Presenter {

    private static final String ERROR = "ERROR";

    private final Context mContext;

    /**
     * Constructor
     */
    public LoginPresenter(Context context) {
        mContext = context;
    }

    /**
     * Handles the logging-in of the user.
     */
    public void login(String email, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        /**
         * Signs user in with firebase
         */
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ERROR, "Signing-in successful.");

                            mContext.startActivity(new Intent(mContext, HomeActivity.class));
                        } else {
                            // If sign in fails, display an error message.
                            Log.d(ERROR, "Signing-in failed.");

                            return;
                        }
                    }
                });
        // Directs newly logged-in user to the app home screen.
        mContext.startActivity(new Intent(mContext, HomeActivity.class));
    }

    /**
     * Ensures that the required fields are filled-in.
     */
    @Override
    public boolean validate(EditText emailET, EditText passwordET) {
        boolean valid = true;

        String requiredField = mContext.getResources().getString(R.string.required_field);

        String email = emailET.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailET.setError(requiredField);
            valid = false;
        } else {
            emailET.setError(null);
        }

        String password = passwordET.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordET.setError(requiredField);
            valid = false;
        } else {
            passwordET.setError(null);
        }

        return valid;
    }

    /**
     * Starts
     */
    @Override
    public void start() {

    }
}
