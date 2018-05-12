package com.jkt48.vbast.laundry_online;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView txt_title_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        //Typeface
        Typeface lobster = Typeface.createFromAsset(getAssets(),"Lobster.ttf");

        //Initial TextView
        txt_title_login = (TextView)findViewById(R.id.txt_title_login);

        txt_title_login.setTypeface(lobster);
    }
}
