package com.jkt48.vbast.laundry_online;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerViewTungguAdapter;
import Model.getDataTungguAdapter;
import Util.Server;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Tunggu extends AppCompatActivity {

    String kd_member;
    public String URL_TUNGGU = Server.URL+"tunggu.php?id=";
    public String TAG_ID = "kd_transaksi";
    public String TAG_KD_MEMBER = "kd_member";
    String TAG_NAMA = "nama_member";
    String TAG_ALAMAT = "alamat";
    public String TAG_TGL_TRANSAKSI = "tgl_transaksi";
    public String TAG_STATUS = "status";
    public String TAG_BERAT = "berat";
    public String TAG_TOTAL = "total";

    SweetAlertDialog sweetAlertDialog;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;

    List<getDataTungguAdapter> getDataTungguAdapters;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerLayoutManager;
    RecyclerView.Adapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tunggu);
        getSupportActionBar().setTitle("Tunggu");
        kd_member = getIntent().getStringExtra("id");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        sweetAlertDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);


        RecyclerView();
        JSON_WEB_CALL();
    }
    private void RecyclerView(){
        recyclerView = (RecyclerView)findViewById(R.id.rv_tunggu);
        recyclerView.setHasFixedSize(true);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);

        getDataTungguAdapters = new ArrayList<>();

    }
    public void JSON_WEB_CALL(){
        sweetAlertDialog.setTitleText("Tunggu");
        sweetAlertDialog.show();
        sweetAlertDialog.setCancelable(false);
        jsonArrayRequest = new JsonArrayRequest(URL_TUNGGU + kd_member,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sweetAlertDialog.dismiss();
                //DIgunakan UNtuk mengechek Data Materi if kosong maka intent pesan akan muncul
                if(response.length() == 0) {
                    startActivity(new Intent(getApplicationContext(),Not_Found.class));
                    finish();
                }else{
                    JSON_PARSE_DATA(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public Void JSON_PARSE_DATA(JSONArray array){

            for(int i = 0; i < array.length();i++){
                getDataTungguAdapter getDataTungguAdapters1 = new getDataTungguAdapter();
                JSONObject jsonObject = null;

                try {
                    jsonObject = array.getJSONObject(i);

                        getDataTungguAdapters1.setKd_transaksi(jsonObject.getString(TAG_ID));
                        getDataTungguAdapters1.setNama_member(jsonObject.getString(TAG_NAMA));
                        getDataTungguAdapters1.setAlamat(jsonObject.getString(TAG_ALAMAT));
                        getDataTungguAdapters1.setTgl_transaksi(jsonObject.getString(TAG_TGL_TRANSAKSI));
                        getDataTungguAdapters1.setStatus(jsonObject.getString(TAG_STATUS));
                        getDataTungguAdapters1.setBerat(jsonObject.getString(TAG_BERAT));
                        getDataTungguAdapters1.setTotal(jsonObject.getString(TAG_TOTAL));

                }catch (Exception e){
                    e.printStackTrace();
                }

                getDataTungguAdapters.add(getDataTungguAdapters1);
            }
            recyclerAdapter = new RecyclerViewTungguAdapter(getDataTungguAdapters,this);
            recyclerView.setAdapter(recyclerAdapter);


        return null;
    }

    @Override
    protected void onStop() {
        super.onStop();
       Toast.makeText(this,"On Stop",Toast.LENGTH_LONG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"On Pause",Toast.LENGTH_LONG);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"Back",Toast.LENGTH_LONG);
    }
}
