package com.mad.assignment.presenter;

import android.widget.TextView;

import com.mad.assignment.BasePresenter;
import com.mad.assignment.BaseView;

/**
 * Interface for account.
 */
public interface MyAccountContract {

    interface View extends BaseView<RegisterContract.Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * Loads account information.
         */
        void loadData(TextView email, TextView name, TextView surname, TextView phone, TextView address);
    }
}
