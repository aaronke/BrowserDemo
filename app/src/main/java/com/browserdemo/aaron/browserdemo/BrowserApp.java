package com.browserdemo.aaron.browserdemo;

import android.app.Application;

import com.browserdemo.aaron.browserdemo.manager.DataManager;
import com.browserdemo.aaron.browserdemo.manager.DatabaseManager;

/**
 * Created by Aaronke on 3/18/2016.
 */
public class BrowserApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        // SingleTons
        DataManager.getOurInstance().init(getApplicationContext());
        DatabaseManager.getOurInstance().init(getApplicationContext());
    }
}
