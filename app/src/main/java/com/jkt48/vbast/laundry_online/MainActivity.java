package com.jkt48.vbast.laundry_online;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btn_Pesan,btn_Tunggu,btn_Bayar;
    TextView tv_email_view;
    Intent a;

    String kd,email;
    SharedPreferences sharedPreferences;

    public static final String TAG_KD = "kd";
    public static final String TAG_EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Login.my_shared_preference, Context.MODE_PRIVATE);
        kd = getIntent().getStringExtra(TAG_KD);
        email = getIntent().getStringExtra(TAG_EMAIL);


        getSupportActionBar().setTitle(email);

        btn_Pesan = (Button)findViewById(R.id.btn_pesan);


        btn_Pesan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent klik = new Intent(getApplicationContext(),Pesan.class);
                klik.putExtra("kd",getIntent().getStringExtra(TAG_KD));
                klik.putExtra("email",getIntent().getStringExtra(TAG_EMAIL));
                startActivity(klik);
            }
        });
    }

}
