package com.jkt48.vbast.laundry_online;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import Util.Server;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Pesan extends AppCompatActivity {

    TextView tv_nama_user,tv_alamat,tv_no_hp,tv_Berat,tv_total_bayar,tv_harga_per_kg;
    int berat,total_bayar;
    ImageView img_Tambah,img_Kurang;

    Button btnTransaksi;
    String kd,alamat,berat_kg,harga_per_kg,total;

    SweetAlertDialog sweetAlertDialog;
    RequestQueue requestQueue;
    String Tambah_Pesan = Server.URL+"transaksi.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        Intent over = getIntent();
        tv_nama_user = (TextView)findViewById(R.id.txt_user_nama);
        tv_no_hp = (TextView)findViewById(R.id.txt_user_no_hp);
        tv_alamat = (TextView)findViewById(R.id.txt_user_alamat);
        tv_harga_per_kg = (TextView)findViewById(R.id.txt_harga_per_kg);

        btnTransaksi = (Button)findViewById(R.id.btn_transaksi);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        tv_nama_user.setText(over.getStringExtra("nama"));
        tv_no_hp.setText(over.getStringExtra("no_hp"));
        tv_alamat.setText(over.getStringExtra("alamat"));

        tv_Berat = (TextView)findViewById(R.id.berat_pesan);
        tv_total_bayar = (TextView)findViewById(R.id.tv_total_bayar);

        getSupportActionBar().setTitle("Pesan");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

        img_Tambah = (ImageView)findViewById(R.id.img_tambah);
        img_Kurang = (ImageView)findViewById(R.id.img_kurang);


        berat = Integer.parseInt(tv_Berat.getText().toString());

        img_Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Membangun Dan MenSet-Up Notification Dengan NotificationCompat.Builder
                /*NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext()).
                        setSmallIcon(R.drawable.clothes).//Memberikan Icon
                        setContentTitle("Notifikasi Saya").//Memberikan Title
                        setAutoCancel(true).//Untuk MenSwipe atau Menghapus Notifikasi
                        setContentText("Selamat Bergabung");//Isi Text*/

                /*

                Kemudian kita harus menambahkan Notification dengan Menggunakan Notification Manager

                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1 ,builder.build()); */

                int total =  berat ++;
                int bayar = total * 10000;

                tv_total_bayar.setText(String.valueOf(bayar));
                tv_Berat.setText(String.valueOf(total));


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
        sweetAlertDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        btnTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sweetAlertDialog.setTitleText("Tunggu....");
                sweetAlertDialog.show();

                //Memanggil Method getValue;
                getValue();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Tambah_Pesan, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        sweetAlertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sweetAlertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("kd_member",kd);
                        params.put("berat",berat_kg);
                        params.put("harga_per_kg",harga_per_kg);
                        params.put("total",total);

                        return params;
                    }

                };
                requestQueue.add(stringRequest);
            }
        });
    }

    public void getValue(){
        kd = getIntent().getStringExtra("kd");
        berat_kg = tv_Berat.getText().toString();
        harga_per_kg = tv_harga_per_kg.getText().toString();
        total = tv_total_bayar.getText().toString();

    }
}
