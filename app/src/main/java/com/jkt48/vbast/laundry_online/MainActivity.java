package com.jkt48.vbast.laundry_online;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import Util.json_view;

public class MainActivity extends AppCompatActivity {
    Button btn_Pesan,btn_Tunggu,btn_Bayar;
    TextView tv_email_view;
    Intent a;

    String kd,email,nama,no_hp,alamat;
    SharedPreferences sharedPreferences;

    private static final String TAG_KD = "kd";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_NAMA = "nama";
    private static final String TAG_NO_HP = "no_hp";
    private static final String TAG_ALAMAT = "alamat";

    json_view json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 5);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_HALF_HOUR,broadcast);
        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);


        inisialisasi();
        bayar();
        tunggu();
        pesan();


    }

    private void inisialisasi(){

        sharedPreferences = getSharedPreferences(Login.my_shared_preference, Context.MODE_PRIVATE);
        kd = getIntent().getStringExtra(TAG_KD);
        email = getIntent().getStringExtra(TAG_EMAIL);
        nama = getIntent().getStringExtra(TAG_NAMA);
        no_hp = getIntent().getStringExtra(TAG_NO_HP);
        alamat = getIntent().getStringExtra(TAG_ALAMAT);

        btn_Pesan = (Button)findViewById(R.id.btn_pesan);
        btn_Tunggu = (Button)findViewById(R.id.btn_tunggu);
        btn_Bayar = (Button)findViewById(R.id.btn_bayar);
    }

    private void bayar(){
        btn_Bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),Bayar.class);
                intent.putExtra("id",kd);
                startActivity(intent);

            }
        });
    }
    private void tunggu(){
        btn_Tunggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent klik = new Intent(getApplicationContext(),Tunggu.class);
                klik.putExtra("id",getIntent().getStringExtra(TAG_KD));
                startActivity(klik);
            }
        });
    }
    private void pesan(){
        btn_Pesan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent klik = new Intent(getApplicationContext(),Pesan.class);
                klik.putExtra("kd",getIntent().getStringExtra(TAG_KD));
                klik.putExtra("email",getIntent().getStringExtra(TAG_EMAIL));
                klik.putExtra("nama",getIntent().getStringExtra(TAG_NAMA));
                klik.putExtra("no_hp",getIntent().getStringExtra(TAG_NO_HP));
                klik.putExtra("alamat",getIntent().getStringExtra(TAG_ALAMAT));

                startActivity(klik);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.class.getSimpleName(),"hALLO");
        Toast.makeText(this,"Halo",Toast.LENGTH_LONG);
    }
}
