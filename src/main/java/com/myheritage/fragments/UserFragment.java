package com.myheritage.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.myheritage.R;
import com.myheritage.activities.MainActivity;
import com.myheritage.loaders.UserLoader;
import com.myheritage.model.User;

import java.util.List;


/**
 * Created by Noa on 21/05/2016.
 */
public class UserFragment extends Fragment {

    private static final String TAG = "user-fragment";
    private static final int LOADER_ID = 101;

    UserListAdapter mAdapter;

    private String userId;

    private TextView first_name, last_name;

    public static UserFragment newInstance(String name) {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);

        //binding...
        first_name = (TextView)view.findViewById(R.id.first_name);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<User>() {
            @Override
            public Loader<User> onCreateLoader(int id, Bundle args) {
                return new UserLoader(getActivity(), ((MainActivity) getActivity()).getMyHeritageRepo(), userId);
            }

            @Override
            public void onLoadFinished(Loader<User> loader, User data) {
                updateUI(data);
            }

            @Override
            public void onLoaderReset(Loader<User> loader) {
                // Clear the data in the adapter.
                mAdapter.setData(null);
            }
        });
    }

    private void updateUI(User user) {
        // update the ui with the user object

        first_name.setText(user.firstName);
        // etc...


        // for the relatives list -->
        mAdapter.setData(user.relationShipUsers);
    }


    public static class UserListAdapter extends ArrayAdapter<User>
    {

        public UserListAdapter(Context context, int resource) {
            super(context, resource);
        }

        public void setData(List<User> data) {
            clear();
            if (data != null) {
                addAll(data);
            }
        }

        // getview should be implemented...
    }
}
