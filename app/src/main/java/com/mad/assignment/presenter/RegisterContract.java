package com.mad.assignment.presenter;

import android.widget.EditText;

import com.mad.assignment.BasePresenter;
import com.mad.assignment.BaseView;

/**
 * Interface for register account.
 */
public interface RegisterContract {

    interface View extends BaseView<RegisterContract.Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * Handles registering of account.
         */
        void register(String email, String password, String name, String surname, String phone, String address);

        /**
         * Handles comparing of passwords.
         */
        boolean comparePasswords(EditText passwordET, EditText confirmET);

        /**
         * Handles validation of fields.
         */
        boolean validate(EditText emailET, EditText passwordET, EditText confirmET, EditText nameET, EditText surnameET, EditText phoneET, EditText addressET);

        /**
         * Handles saving of new user details.
         */
        void saveUser(String emailAddress, String firstName, String lastName, String phoneNumber, String address);
    }
}
