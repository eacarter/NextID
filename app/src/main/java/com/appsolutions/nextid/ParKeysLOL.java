package com.appsolutions.nextid;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseACL;

/**
 * Created by DaddyZephr2 on 8/6/2015.
 */
public class ParKeysLOL extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "sIEautr3KKdQl4HGdd4ta1AQb0K5XuqGtbtrnx3o", "XbgFAfCivupf0XJ8nSfx0OX5mjRBDQEBx0QoZFS0");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
