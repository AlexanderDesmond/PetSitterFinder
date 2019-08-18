package com.mad.assignment.presenter;

import android.widget.EditText;

import com.mad.assignment.BasePresenter;
import com.mad.assignment.BaseView;

/**
 * Interface for edit account.
 */
public interface EditAccountContract {

    interface View extends BaseView<RegisterContract.Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * Updates account details.
         */
        void editAccountDetails(EditText nameET, EditText surnameET, EditText passwordET, EditText confirmPasswordET, EditText phoneET, EditText addressET);
    }
}
