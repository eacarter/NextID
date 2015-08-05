package com.appsolutions.nextid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.Parse;
import com.parse.FindCallback;

import java.util.ArrayList;


public class MainLoginActivity extends ActionBarActivity  {

    TextView h1;
    TextView h2;
    Spinner p;
    Animation fader;

    ArrayList<String> pen15 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Parse.initialize(this, "sIEautr3KKdQl4HGdd4ta1AQb0K5XuqGtbtrnx3o", "XbgFAfCivupf0XJ8nSfx0OX5mjRBDQEBx0QoZFS0");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("companies");
        query.selectKeys(Arrays.asList("compName"));


        h1 = (TextView)findViewById(R.id.welcome);
        h2 = (TextView)findViewById(R.id.text);
        p = (Spinner)findViewById(R.id.spinner);





        fader = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        h1.setVisibility(View.VISIBLE);
        h1.setAnimation(fader);
        h2.setVisibility(View.VISIBLE);
        h2.setAnimation(fader);
        p.setVisibility(View.VISIBLE);
        p.setAnimation(fader);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pen15);
        p.setAdapter(adapter);

    }


}
