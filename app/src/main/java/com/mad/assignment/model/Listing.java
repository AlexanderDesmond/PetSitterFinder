package com.mad.assignment.model;

/**
 * Handles creation of listing object.
 */
public class Listing {

    private String mListingId, mUserId, mDescription, mAvailabilityFrom, mAvailabilityTo, mNumberOfAnimals, mOthers;
    private boolean mDog, mCat, mGuineaPig, mAmphibian, mReptile;

    /**
     * Default constructor.
     */
    public Listing() {}

    /**
     * Constructor to create a new listing object.
     */
    public Listing(String listingId, String userId, String description, String availabilityFrom, String availabilityTo, boolean dog, boolean cat, boolean guineaPig, boolean amphibian, boolean reptile, String others, String numberOfAnimals) {
        mListingId = listingId;
        mUserId = userId;
        mDescription = description;
        mAvailabilityFrom = availabilityFrom;
        mAvailabilityTo = availabilityTo;
        mDog = dog;
        mCat = cat;
        mGuineaPig = guineaPig;
        mAmphibian = amphibian;
        mReptile = reptile;
        mOthers = others;
        mNumberOfAnimals = numberOfAnimals;
    }

    /**
     * Gets listing id.
     */
    public String getListingId() {
        return mListingId;
    }

    /**
     * Sets listing id.
     */
    public void setListingId(String listingId) {
        mListingId = listingId;
    }

    /**
     * Gets user id.
     */
    public String getUserId() {
        return mUserId;
    }

    /**
     * Sets user id.
     */
    public void setUserId(String userId) {
        mUserId = userId;
    }

    /**
     * Gets description.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Sets description.
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * Gets availability from date.
     */
    public String getAvailabilityFrom() {
        return mAvailabilityFrom;
    }

    /**
     * Sets availability from date.
     */
    public void setAvailabilityFrom(String availabilityFrom) {
        mAvailabilityFrom = availabilityFrom;
    }

    /**
     * Gets availability to date.
     */
    public String getAvailabilityTo() {
        return mAvailabilityTo;
    }

    /**
     * Sets availability to date.
     */
    public void setAvailabilityTo(String availabilityTo) {
        mAvailabilityTo = availabilityTo;
    }

    /**
     * Gets number of animals.
     */
    public String getNumberOfAnimals() {
        return mNumberOfAnimals;
    }

    /**
     * Sets number of animals.
     */
    public void setNumberOfAnimals(String numberOfAnimals) {
        mNumberOfAnimals = numberOfAnimals;
    }

    /**
     * Gets others.
     */
    public String getOthers() {
        return mOthers;
    }

    /**
     * Sets others.
     */
    public void setOthers(String others) {
        mOthers = others;
    }

    /**
     * Gets dog.
     */
    public boolean isDog() {
        return mDog;
    }

    /**
     * Sets dog.
     */
    public void setDog(boolean dog) {
        mDog = dog;
    }

    /**
     * Gets cat.
     */
    public boolean isCat() {
        return mCat;
    }

    /**
     * Sets cat.
     */
    public void setCat(boolean cat) {
        mCat = cat;
    }

    /**
     * Gets guinea pig.
     */
    public boolean isGuineaPig() {
        return mGuineaPig;
    }

    /**
     * Sets guinea pig.
     */
    public void setGuineaPig(boolean guineaPig) {
        mGuineaPig = guineaPig;
    }

    /**
     * Gets amphibian.
     */
    public boolean isAmphibian() {
        return mAmphibian;
    }

    /**
     * Sets amphibian.
     */
    public void setAmphibian(boolean amphibian) {
        mAmphibian = amphibian;
    }

    /**
     * Gets reptile.
     */
    public boolean isReptile() {
        return mReptile;
    }

    /**
     * Sets reptile.
     */
    public void setReptile(boolean reptile) {
        mReptile = reptile;
    }
}
