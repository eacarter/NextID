package com.appsolutions.nextid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class LoginActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttons = (Button)findViewById(R.id.button);

        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(LoginActivity.this, MainLoginActivity.class);
                startActivity(login);
                overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);

            }
        });


    }

    public void onBackPressed(){
        this.finish();
        overridePendingTransition(R.anim.right_slide_in,R.anim.right_slide_out);
    }
}
