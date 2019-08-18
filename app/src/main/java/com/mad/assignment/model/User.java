package com.mad.assignment.model;

/**
 * Handles creation of user object.
 */
public class User {

    private String mEmailAddress, mFirstName, mLastName, mPhoneNumber, mAddress;

    /**
     * Default constructor.
     */
    public User() {}

    /**
     * Constructor to create a new user object.
     */
    public User(String emailAddress, String firstName, String lastName, String phoneNumber, String address) {
        mEmailAddress = emailAddress;
        mFirstName = firstName;
        mLastName = lastName;
        mPhoneNumber = phoneNumber;
        mAddress = address;
    }

    /**
     * Gets email address.
     */
    public String getEmailAddress() {
        return mEmailAddress;
    }

    /**
     * Sets email address.
     */
    public void setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
    }

    /**
     * Gets first name.
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Sets first name.
     */
    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    /**
     * Gets last name.
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * Sets last name.
     */
    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    /**
     * Gets phone number.
     */
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    /**
     * Sets phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    /**
     * Gets address.
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * Sets address.
     */
    public void setAddress(String address) {
        mAddress = address;
    }
}
