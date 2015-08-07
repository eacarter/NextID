package com.appsolutions.nextid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.Parse;



public class MainLoginActivity extends ActionBarActivity  {

    TextView h1;
    TextView h2;
    ListView p;
    Animation fader;

    ParseQueryAdapter<ParseObject> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        adapter = new ParseQueryAdapter<ParseObject>(this, "companies");
        adapter.setTextKey("compName");

        h1 = (TextView)findViewById(R.id.welcome);
        h2 = (TextView)findViewById(R.id.text);
        p = (ListView)findViewById(R.id.spinner);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {

           public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainLoginActivity.this, ActualLoginActivity.class);
                intent.putExtra("compName", adapter.getItem(position).getString("compName"));
                startActivity(intent);
           }

        };
        p.setOnItemClickListener(listener);

        fader = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        h1.setVisibility(View.VISIBLE);
        h1.setAnimation(fader);
        h2.setVisibility(View.VISIBLE);
        h2.setAnimation(fader);
        p.setVisibility(View.VISIBLE);
        p.setAnimation(fader);

        p.setAdapter(adapter);


    }

    public void onBackPressed(){
        this.finish();
        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);

    }


}
