package com.mad.assignment.presenter;

import android.widget.TextView;

import com.mad.assignment.BasePresenter;
import com.mad.assignment.BaseView;

/**
 * Interface for view listing
 */
public interface ViewListingContract {
    interface View extends BaseView<RegisterContract.Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * Gets user data from database.
         */
        void getUserData(TextView nameTV, TextView addressTV, TextView phoneTV, String listingPoster);

        /**
         * Gets listing data from database.
         */
        void getListingData(TextView availabilityTV, TextView numberTV, String listingKey);

        /**
         * Handles sending of email.
         */
        void sendEmail(String email);

        /**
         * Handles sending of message.
         */
        void sendMessage(String number);

        /**
         * Returns email address
         */
        String getEmail();

        /**
         * Returns phone number
         */
        String getNumber();
    }
}
