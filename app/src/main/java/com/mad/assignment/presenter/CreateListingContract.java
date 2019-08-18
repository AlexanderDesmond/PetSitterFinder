package com.mad.assignment.presenter;

import android.support.design.widget.TextInputEditText;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mad.assignment.BasePresenter;
import com.mad.assignment.BaseView;

/**
 * Interface for creating a listing.
 */
public interface CreateListingContract {

    interface View extends BaseView<RegisterContract.Presenter> {}

    interface Presenter extends BasePresenter {
        /**
         * Creates a new listing.
         */
        void createListing(TextInputEditText descriptionTI, String availabilityFrom, String availabilityTo, CheckBox dogCB, CheckBox catCB, CheckBox guineaPigCB, CheckBox amphibianCB, CheckBox reptileCB, EditText othersET, EditText numberET);
        /**
         * Validates form.
         */
        boolean validate(TextInputEditText inputTI, String dateFrom, String dateTo, EditText numberET);
        /**
         * Checks if pet is present in listing.
         */
        boolean ifPet(CheckBox checkBox);
        /**
         * Formats date data.
         */
        String formatData(int day, int month, int year);
    }
}
