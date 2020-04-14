package com.example.android.quakereport;

import android.app.Application;
import android.content.Context;

public class CommonApplication extends Application {
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }
}
