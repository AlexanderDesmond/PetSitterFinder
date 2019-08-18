package com.mad.assignment.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

// Added
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.mad.assignment.R;
import com.mad.assignment.presenter.CreateListingPresenter;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Class to handle the activity CreateListingActivity.
 */
public class CreateListingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String DATE_PICKER_DIALOG = "Datepickerdialog";

    // Instantiates views.
    @BindView(R.id.createListing_description_editText) TextInputEditText mDescriptionTextInputEditText;
    @BindView(R.id.createListing_availability_textView) TextView mAvailabilityTextView;
    @BindView(R.id.dog_checkBox) CheckBox mDogCheckBox;
    @BindView(R.id.cat_checkBox) CheckBox mDCatCheckBox;
    @BindView(R.id.guineaPig_checkBox) CheckBox mGuineaPigCheckBox;
    @BindView(R.id.amphibians_checkBox) CheckBox mAmphibianCheckBox;
    @BindView(R.id.reptiles_checkBox) CheckBox mReptileCheckBox;
    @BindView(R.id.createListing_others_editText) EditText mOthersEditText;
    @BindView(R.id.createListing_number_editText) EditText mNumberEditText;

    // Instantiates string resources
    @BindString(R.string.required_field) String mRequiredField;
    @BindString(R.string.others_toastMessage) String mToastMessage;

    private String mDateFrom;
    private String mDateTo;

    // Presenter object
    private CreateListingPresenter mPresenter;

    /**
     * Handles the creation of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        // Binds views.
        ButterKnife.bind(this);

        // Instantiates presenter object.
        mPresenter = new CreateListingPresenter(getApplicationContext());
    }

    /**
     * Handles the creation/loading of the date picker dialog.
     */
    @OnClick(R.id.createListing_setAvailability_button)
    public void selectDates() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(CreateListingActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show(getFragmentManager(), DATE_PICKER_DIALOG);
    }

    /**
     * Handles the response to the others edit text field being touched.
     */
    @OnTouch(R.id.createListing_others_editText)
    public boolean showOthersMessage() {
        Toast.makeText(getApplicationContext(), mToastMessage, Toast.LENGTH_LONG).show();
        return false;
    }

    /**
     * Handles the creation of a new listing.
     */
    public void submit(View v) {
        // Creates new listing.
        mPresenter.createListing(mDescriptionTextInputEditText, mDateFrom, mDateTo, mDogCheckBox, mDCatCheckBox, mGuineaPigCheckBox, mAmphibianCheckBox, mReptileCheckBox, mOthersEditText, mNumberEditText);
    }

    /**
     * Handles the selection of dates from the date picker dialog.
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        // Gets appropriately formatted dates.
        mDateFrom = mPresenter.formatData(dayOfMonth, monthOfYear, year);
        mDateTo = mPresenter.formatData(dayOfMonthEnd, monthOfYearEnd, yearEnd);

        mAvailabilityTextView.setText(mDateFrom + " - " + mDateTo);
    }
}
