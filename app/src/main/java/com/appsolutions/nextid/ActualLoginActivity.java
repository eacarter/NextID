package com.appsolutions.nextid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.view.animation.AnimationUtils;


import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.Calendar;


public class ActualLoginActivity extends ActionBarActivity {

    String name;
    TextView welc;
    TextView comp;
    TextView emp;
    TextView subBut;
    EditText userN;
    EditText pass;
    CheckBox check;
    Intent in;
    Intent out;

    private SharedPreferences loginPreferences;
    public SharedPreferences.Editor loginPrefsEditor;
    boolean saveLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_login);

        welc = (TextView) findViewById(R.id.welcome);
        comp = (TextView) findViewById(R.id.employment);
        emp = (TextView) findViewById(R.id.employeeText);
        subBut = (TextView) findViewById(R.id.sub);
        userN = (EditText) findViewById(R.id.textfield1);
        pass = (EditText) findViewById(R.id.textfield2);
        check = (CheckBox)findViewById(R.id.check);
        loginPreferences = getSharedPreferences("logPre", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        in = getIntent();
        name = in.getExtras().getString("compName");

        comp.setText(name.toString());

        saveLog = loginPreferences.getBoolean("saveLogin", false);


        if(saveLog == true){
            userN.setText(loginPreferences.getString("Username", ""));
            pass.setText(loginPreferences.getString("Password", ""));
            check.setChecked(true);
        }
        subBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager putter = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                putter.hideSoftInputFromWindow(userN.getWindowToken(), 0);

                String userID = userN.getText().toString();
                String passID = pass.getText().toString();

                if(check.isChecked()){
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("Username", userID);
                    loginPrefsEditor.putString("Password", passID);
                    loginPrefsEditor.commit();
                }
                else{
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

                    ParseUser.logInInBackground(userID, passID, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (e != null) {
                                Toast.makeText(getApplication(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                if (user.get("Company").equals(name)) {
                                    out = new Intent(ActualLoginActivity.this, keyCard.class);
                                    startActivity(out);
                                    finish();
                                } else {
                                    Toast.makeText(getApplication(), "shit not workin bruh", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                }
            });
        }
    }
