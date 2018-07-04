package com.jkt48.vbast.laundry_online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Not_Found extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not__found);
        getSupportActionBar().setTitle("Not Found");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
    }
}
