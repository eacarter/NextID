package com.appsolutions.nextid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.parse.ParseQuery;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.GetDataCallback;
import com.parse.ParseUser;



import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class keyCard extends ActionBarActivity {

    ImageView profpic;
    TextView fullName;
    TextView position;
    TextView employ;
    ImageView unlock;
    Intent in;
    String camp;
    String flname;
    String pos;
    String objID;
    Bitmap bitmap;
    Byte [] data;

    ParseUser parseUser;
    boolean buto = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_card);

        in = getIntent();
        flname = in.getExtras().getString("firname") + " " + in.getExtras().getString("lasname");
        pos = in.getExtras().getString("position");
        camp = in.getExtras().getString("comp");
        objID = in.getExtras().getString("objectid");

        profpic = (ImageView)findViewById(R.id.picture);
        fullName = (TextView)findViewById(R.id.name);
        position = (TextView)findViewById(R.id.position);
        employ = (TextView)findViewById(R.id.company);
        unlock = (ImageView)findViewById(R.id.lockunlock);


        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("objectId", objID);
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            public void done(ParseUser object, ParseException e) {

                if (object != null) {

                    ParseFile file = (ParseFile) object.get("profPic");
                    file.getDataInBackground(new GetDataCallback() {


                        public void done(byte[] data, ParseException e) {
                            if (e == null) {

                                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                profpic.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 210, 180, false));



                            } else {

                                profpic.setImageResource(R.drawable.profpic);
                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();

                }
            }
        });

        fullName.setText(flname.toString());
        position.setText(pos.toString());
        employ.setText(camp.toString());


        profpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });





        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(buto == true) {
                    unlock.setImageResource(R.drawable.unlockicon);

                    // code for NFC turn on goes here

                    buto = false;
                } else {
                    unlock.setImageResource(R.drawable.lockicon);

                    // code for NFC turn off goes here

                    buto = true;
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        profpic.setImageBitmap(bp);

        ByteArrayOutputStream blob = new ByteArrayOutputStream();

        bp.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, blob);

        byte[] imgArray = blob.toByteArray();

        //Assign Byte array to ParseFile
        ParseFile parseImagefile = new ParseFile("profile_pic.png", imgArray);

        parseUser.getCurrentUser().put("profPic", parseImagefile);
        parseUser.getCurrentUser().saveInBackground();


        //addidtional code to push data up to parse goes here;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
