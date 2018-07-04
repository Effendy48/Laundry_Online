package com.jkt48.vbast.laundry_online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

import java.util.List;

import Model.getDataBayarAdapter;
import Util.Server;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Bayar extends AppCompatActivity {
    String URL = Server.URL+"bayar.php?id=";
    public String TAG_KD = "kd_transaksi";
    public String TAG_KD_MEMBER = "kd_member";
    public String TAG_ALAMAT = "alamat";
    public String TAG_TGL_TRANSAKSI = "tgl_transaksi";
    public String TAG_BERAT = "berat";
    public String TAG_TOTAL = "total";

    SweetAlertDialog sweetAlertDialog;

    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;

    List<getDataBayarAdapter> getDataBayarAdapters;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerLayoutManager;
    RecyclerView.Adapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
    }
}
