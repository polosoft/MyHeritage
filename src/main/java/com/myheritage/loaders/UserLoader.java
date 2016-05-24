package com.myheritage.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.myheritage.dao.MyHeritageRepo;
import com.myheritage.model.User;

import java.sql.SQLException;

/**
 * Created by Noa on 23/05/2016.
 */
public class UserLoader extends AsyncTaskLoader<User> {
    private static final String LOG_TAG = UserLoader.class.getSimpleName();

    // DbBase is the parent class of all ORMLite data objects.
    private User mData;
    private String userId;
    private MyHeritageRepo myHeritageRepo;

    public UserLoader(Context context, MyHeritageRepo _userRepo, String userId) {
        super(context);
        this.userId = userId;
        this.myHeritageRepo = _userRepo;
    }

    @Override
    public User loadInBackground() {
        User user = null;

        try {
            user = myHeritageRepo.GetUser("1");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deliverResult(User data) {
        if (isReset()) {
            return;
        }

        mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        // Ensure the loader has been stopped.
        onStopLoading();

        // At this point we can release the resources associated with 'mData'.
        if (mData != null) {
            mData = null;
        }
    }




}