package com.mad.assignment.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mad.assignment.R;
import com.mad.assignment.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class to handle the activity LoginActivity.
 */
public class LoginActivity extends AppCompatActivity {

    // Instantiates the views.
    @BindView(R.id.login_email_editText) EditText mEmailEditText;
    @BindView(R.id.login_password_editText) EditText mPasswordEditText;

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Binds views.
        ButterKnife.bind(this);
    }

    /**
     * Handles the logging-in of the user.
     */
    public void login(View v) {
        // Ensures that both fields are properly filled-in.
        if (!new LoginPresenter(getApplicationContext()).validate(mEmailEditText, mPasswordEditText)) return;

        // Attempts to log-in the user.
        new LoginPresenter(getApplicationContext()).login(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
    }
}
