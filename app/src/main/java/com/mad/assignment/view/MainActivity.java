package com.mad.assignment.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mad.assignment.R;

/**
 * Class to handle the activity MainActivity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts LoginActivity activity.
     */
    public void login(View v) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     *
     * Starts RegisterActivity activity.
     */
    public void register(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
