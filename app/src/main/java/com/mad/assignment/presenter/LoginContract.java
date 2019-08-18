package com.mad.assignment.presenter;

import android.widget.EditText;

import com.mad.assignment.BasePresenter;
import com.mad.assignment.BaseView;

/**
 * Interface for login
 */
public interface LoginContract {

    interface View extends BaseView<LoginContract.Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * Validates login info
         */
        boolean validate(EditText emailET, EditText passwordET);
    }
}
