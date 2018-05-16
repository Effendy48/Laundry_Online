package com.jkt48.vbast.laundry_online;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Pesan extends AppCompatActivity {
    EditText et_User;
    TextView tv_Berat,tv_total_bayar;
    int berat,total_bayar;
    ImageView img_Tambah,img_Kurang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        Intent over = getIntent();
        et_User = (EditText)findViewById(R.id.et_user_pesan);
        et_User.setText(over.getStringExtra("email"));
        tv_Berat = (TextView)findViewById(R.id.berat_pesan);
        tv_total_bayar = (TextView)findViewById(R.id.tv_total_bayar);


        img_Tambah = (ImageView)findViewById(R.id.img_tambah);
        img_Kurang = (ImageView)findViewById(R.id.img_kurang);


        berat = Integer.parseInt(tv_Berat.getText().toString());

        img_Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Membangun Dan MenSet-Up Notification Dengan NotificationCompat.Builder
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext()).
                        setSmallIcon(R.drawable.clothes).//Memberikan Icon
                        setContentTitle("Notifikasi Saya").//Memberikan Title
                        setAutoCancel(true).//Untuk MenSwipe atau Menghapus Notifikasi
                        setContentText("Selamat Bergabung");//Isi Text

                /*

                Kemudian kita harus menambahkan Notification dengan Menggunakan Notification Manager
                 */
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1 ,builder.build());

                /*int total =  berat ++;
                int bayar = total * 10000;

                tv_total_bayar.setText(String.valueOf(bayar));
                tv_Berat.setText(String.valueOf(total)); */


            }
        });
        img_Kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total_bayar = Integer.parseInt(tv_total_bayar.getText().toString());
                berat = Integer.parseInt(tv_Berat.getText().toString());

                int kurang = new Integer(berat - 1);
                tv_Berat.setText(String.valueOf(kurang));

                int bayar = total_bayar - 10000;

                tv_total_bayar.setText(String.valueOf(bayar));
                tv_Berat.setText(String.valueOf(kurang));

            }
        });

    }
}
