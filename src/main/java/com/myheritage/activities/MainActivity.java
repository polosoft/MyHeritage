package com.myheritage.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.myheritage.R;
import com.myheritage.dao.MyHeritageRepo;
import com.myheritage.fragments.UserFragment;
import com.myheritage.model.RelationShipUsers;
import com.myheritage.model.User;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private MyHeritageRepo myHeritageRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myHeritageRepo = new MyHeritageRepo();

        User user = new User();
        user.firstName = "noa1";
        user.lastName = "222";
        user.birthDate = "222";
        user.countryOfBirth = "222";

        User user1 = new User();
        user1.firstName = "noa2";
        user1.lastName = "222";
        user1.birthDate = "222";
        user1.countryOfBirth = "222";

        RelationShipUsers relationShipUsers = new RelationShipUsers(user, user1, "1");

        try {
            myHeritageRepo.SaveUser(user);
            myHeritageRepo.SaveUser(user1);
            myHeritageRepo.SaveRelationshipUser(relationShipUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, UserFragment.newInstance("userFragment"))
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public MyHeritageRepo getMyHeritageRepo() {
        return myHeritageRepo;
    }
}
