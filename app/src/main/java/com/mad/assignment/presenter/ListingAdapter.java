package com.mad.assignment.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.mad.assignment.R;
import com.mad.assignment.model.Listing;
import com.mad.assignment.view.ViewListingActivity;

import java.util.ArrayList;

/**
 * Adapter for listing items.
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {

    private static final String USER_ID = "USER_ID";
    private static final String LISTING_KEY = "LISTING_KEY";
    private static final String PETS = "PETS";


    private ArrayList<Listing> mListings;
    private Context mContext;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String mListingKey;

    /**
     * Constructor
     */
    public ListingAdapter(Context context, ArrayList<Listing> listings) {
        mContext = context;
        mListings = listings;
    }

    /**
     * Upon the creation of the view holder.
     */
    @NonNull
    @Override
    public ListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * When the view holder is bound.
     */
    @Override
    public void onBindViewHolder(@NonNull ListingAdapter.ViewHolder holder, int position) {
        Listing listing = mListings.get(position);
        holder.availability.setText(listing.getAvailabilityFrom() + " - " + listing.getAvailabilityTo());
        // Put pets here later!
        final String pets = getPets(listing);
        holder.pets.setText(pets);

        holder.number.setText(listing.getNumberOfAnimals());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(mContext, ViewListingActivity.class);
                intent.putExtra(USER_ID, mAuth.getCurrentUser().getUid());
                intent.putExtra(LISTING_KEY, mListings.get(position).getListingId());
                intent.putExtra(PETS, pets);
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Returns number of items in the list.
     */
    @Override
    public int getItemCount() {
        return mListings.size();
    }

    /**
     * Creates view holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView availability, pets, number;

        private ItemClickListener mItemClickListener;

        /**
         * Constructor
         */
        public ViewHolder(View itemView) {
            super(itemView);

            availability = (TextView) itemView.findViewById(R.id.item_availability_textView);
            pets = (TextView) itemView.findViewById(R.id.item_pets_textView);
            number = (TextView) itemView.findViewById(R.id.item_number_textView);


            itemView.setOnClickListener(this);
        }

        /**
         * Creates an itemclicklistener
         */
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.mItemClickListener = itemClickListener;
        }

        /**
         * When list item is clicked.
         */
        @Override
        public void onClick(View view) {

            mItemClickListener.onClick(view, getAdapterPosition());
        }
    }

    /**
     * Formats pet types.
     */
    private String getPets(Listing listing) {
        String pets = "";

        String dog = mContext.getResources().getString(R.string.dog);
        if (listing.isDog()) {
            pets += dog;
        }
        String cat = mContext.getResources().getString(R.string.cat);
        if (pets.equals("") && listing.isCat()) {
            pets += cat;
        } else if (listing.isCat()) {
            pets += ", " + cat;
        }
        String guineaPig = mContext.getResources().getString(R.string.guinea_pig);
        if (pets.equals("") && listing.isGuineaPig()) {
            pets += guineaPig;
        } else if (listing.isGuineaPig()) {
            pets += ", " + guineaPig;
        }
        String amphibian = mContext.getResources().getString(R.string.amphibians);
        if (pets.equals("") && listing.isAmphibian()) {
            pets += amphibian;
        } else if (listing.isAmphibian()) {
            pets += ", " + amphibian;
        }
        String reptile = mContext.getResources().getString(R.string.reptiles);
        if (pets.equals("") && listing.isReptile()) {
            pets += reptile;
        } else if (listing.isReptile()) {
            pets += ", " +reptile;
        }

        if (!listing.getOthers().equals("")) {
            pets += ", " + listing.getOthers();
        }

        return pets;
    }
}
