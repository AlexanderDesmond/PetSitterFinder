package com.mad.assignment.presenter;

import com.mad.assignment.BasePresenter;
import com.mad.assignment.BaseView;

/**
 * Interface for viewing all listings.
 */
public interface ViewListingsContract {

    interface View extends BaseView<RegisterContract.Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * Creates list of listings.
         */
        void createList();
    }
}
