package com.ribbitree.timeblocks;

import android.app.Application;
import android.content.Context;

public class TimeBlockApplication extends Application {
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            TimeBlockApplication.instance = getApplicationContext();
        }
    }

    public static Context getInstance() {return instance;}

}
