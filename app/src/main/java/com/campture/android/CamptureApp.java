package com.campture.android;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by championswimmer on 31/10/15.
 */
public class CamptureApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "hqRCJWWJJhduQBOceJYMnKUh8rt5prJ2WyUfDkmp", "75v6gpRGyKkJN1QHjlOZUhbaAyn7slykbYnGP3mk");
    }
}
