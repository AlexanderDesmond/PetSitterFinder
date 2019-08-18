package com.mad.assignment.model;

import java.util.ArrayList;

/**
 * Class for a list of users.
 */
public class Users {
    // List of users.
    private ArrayList<User> mUsers = new ArrayList<User>();

    /**
     * Default constructor.
     */
    public Users() {}

    /**
     * Adds a new user to the list.
     */
    public void addUser(User user) {
        mUsers.add(user);
    }
}
