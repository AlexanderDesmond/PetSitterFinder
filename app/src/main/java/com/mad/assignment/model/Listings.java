package com.mad.assignment.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a list of listings.
 */
public class Listings {
    // List of listings.
    private List<Listing> mListings = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Listings() {}

    /**
     * Adds a new listing to the list.
     */
    public void add(Listing listing) {
        mListings.add(listing);
    }
}
