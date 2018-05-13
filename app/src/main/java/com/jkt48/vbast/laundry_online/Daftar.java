package com.jkt48.vbast.laundry_online;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Daftar extends AppCompatActivity {
    TextView txtDaftar;
    Typeface Lobster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().hide();

        Lobster = Typeface.createFromAsset(getAssets(),"Lobster.ttf");
        txtDaftar = (TextView)findViewById(R.id.txt_title_daftar);
        txtDaftar.setTypeface(Lobster);
    }
}
