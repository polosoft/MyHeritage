package com.myheritage;

import android.app.Application;
import android.util.Log;

import com.myheritage.utils.db.HelperFactory;

/**
 * Created by Noa on 23/05/2016.
 */
public class UserClient extends Application {

    private static final String APP_TAG = "WEATHER CLIENT";

    @Override
    public void onCreate(){
        super.onCreate();

        Log.d(APP_TAG, "app started");
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
