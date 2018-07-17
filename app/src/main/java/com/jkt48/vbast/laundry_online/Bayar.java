package com.jkt48.vbast.laundry_online;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerViewBayarAdapter;
import Model.getDataBayarAdapter;
import Util.Server;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Bayar extends AppCompatActivity {
    String URL = Server.URL+"bayar.php?id=";

    String kode_member;
    public String TAG_KD = "kd_transaksi";
    public String TAG_KD_MEMBER = "kd_member";
    public String TAG_ALAMAT = "alamat";
    public String TAG_TGL_TRANSAKSI = "tgl_transaksi";
    public String TAG_BERAT = "berat";
    public String TAG_TOTAL = "total";

    SweetAlertDialog sweetAlertDialog;

    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;

    SwipeRefreshLayout swipeRefreshLayout;

    List<getDataBayarAdapter> getDataBayarAdapters;
    Handler handler;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerLayoutManager;
    RecyclerView.Adapter recyclerAdapter;

    json_view json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout_archive);
        recyclerView = (RecyclerView)findViewById(R.id.rv_bayar);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        kode_member = getIntent().getStringExtra("id");

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        getSupportActionBar().setTitle("History Bayar");

        getDataBayarAdapters = new ArrayList<>();


        swipeRefresh();
        JSON_WEB_CALL();
    }

    public void swipeRefresh(){
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorButtonPesan,R.color.cardview_shadow_start_color);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        overridePendingTransition(0,0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0,0);
                        startActivity(intent);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1500);
            }
        });
    }

    public void JSON_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(URL+kode_member, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length() == 0){
                    startActivity(new Intent(getApplicationContext(),Not_Found.class));
                }else{
                    JSON_AFTER_WEBCALL(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Ada Yang Error",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    public Void JSON_AFTER_WEBCALL(JSONArray array){
        for(int i = 0;i < array.length();i++){
            getDataBayarAdapter getDataBayarAdapter1 = new getDataBayarAdapter();
            JSONObject jsonObject = null;

            try{
                jsonObject = array.getJSONObject(i);
                getDataBayarAdapter1.setKd_member(jsonObject.getString(TAG_KD_MEMBER));
                getDataBayarAdapter1.setKd_transaksi(jsonObject.getString(TAG_KD));
                getDataBayarAdapter1.setBerat(jsonObject.getString(TAG_BERAT));
                getDataBayarAdapter1.setTgl_transaksi(jsonObject.getString(TAG_TGL_TRANSAKSI));
                getDataBayarAdapter1.setAlamat(jsonObject.getString(TAG_ALAMAT));
                getDataBayarAdapter1.setTotal(jsonObject.getString(TAG_TOTAL));
            }catch (Exception e){
                e.printStackTrace();
            }
            getDataBayarAdapters.add(getDataBayarAdapter1);
        }
        recyclerAdapter = new RecyclerViewBayarAdapter(getDataBayarAdapters,getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);
        return null;
    }

}
