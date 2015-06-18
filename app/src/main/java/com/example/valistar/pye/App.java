package com.example.valistar.pye;

/**
 * Created by Valistar on 18/06/2015.
 */
import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "WvIK8S9zuYAjwccHKIRYCvoU0LU39M0tJUPYmklY", "qOnvLRrr1Kz87GuGpQxUxMc2ZoHyhNlWW9f4GPJb"); // Your Application ID and Client Key are defined elsewhere

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
