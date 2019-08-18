package com.mad.assignment.presenter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.assignment.model.Listing;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for viewing pet care listings.
 */
public class ViewListingsPresenter implements ViewListingsContract.Presenter {

    private static final String LISTINGS = "listings";

    // Context
    private final Context mContext;

    RecyclerView mRecyclerView;
    ListingAdapter mListingAdapter;

    private List<Listing> mListings = new ArrayList<>();
    private List<Listing> mDatabaseListings = new ArrayList<>();

    /**
     * Constructor
     */
    public ViewListingsPresenter(Context context, RecyclerView recyclerView) {
        mContext = context;

        mRecyclerView = (RecyclerView) recyclerView;
        mListingAdapter = new ListingAdapter(mContext, (ArrayList<Listing>) mListings);
    }

    /**
     * handles start
     */
    @Override
    public void start() {

    }

    /**
     * Creates recycler view list of listing items.
     */
    @Override
    public void createList() {
        DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference listingsReference = rootReference.child(LISTINGS);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mListingAdapter);

        // Gets new listing data from the database.
        listingsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Listing listing = child.getValue(Listing.class);
                    mDatabaseListings.add(listing);
                }
                prepareListData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Prepares list data for display
     */
    private void prepareListData() {

        for (Listing listing : mDatabaseListings) {
            mListings.add(listing);
        }

        mListingAdapter.notifyDataSetChanged();
    }
}
