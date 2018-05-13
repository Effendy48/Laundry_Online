package com.jkt48.vbast.laundry_online;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView txt_title_login,etdaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        //Typeface
        Typeface lobster = Typeface.createFromAsset(getAssets(),"Lobster.ttf");

        //Initial TextView
        txt_title_login = (TextView)findViewById(R.id.txt_title_login);
        etdaftar = (TextView)findViewById(R.id.et_daftar);
        etdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Daftar.class));
            }
        });

        txt_title_login.setTypeface(lobster);
    }
}
