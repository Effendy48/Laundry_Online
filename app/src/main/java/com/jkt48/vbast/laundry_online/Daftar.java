package com.jkt48.vbast.laundry_online;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import Util.Server;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Daftar extends AppCompatActivity {
    TextView txtDaftar;
    Typeface Lobster;

    String nama,alamat,no,email,pass;

    EditText et_Nama,et_Alamat,et_No,et_Email,et_Password;
    Button btn_Daftar;

    SweetAlertDialog progressDialog;
    RequestQueue requestQueue;

    String ADD_URL = Server.URL+"daftar_member.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().hide();

        //Menginisialisasi View
            et_Nama = (EditText)findViewById(R.id.et_daftar_nama);
            et_Alamat = (EditText)findViewById(R.id.et_daftar_alamat);
            et_No = (EditText)findViewById(R.id.et_daftar_no_hp);
            et_Email = (EditText)findViewById(R.id.et_daftar_email);
            et_Password = (EditText)findViewById(R.id.et_daftar_password);

            btn_Daftar = (Button)findViewById(R.id.btn_daftar);

        //End

        Lobster = Typeface.createFromAsset(getAssets(),"Lobster.ttf");
        txtDaftar = (TextView)findViewById(R.id.txt_title_daftar);
        txtDaftar.setTypeface(Lobster);
        //Membuat Volley RequestQueue
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Membuat Inisiasi Dialog
            progressDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        //End


        //Action Pada Btn Daftar
            btn_Daftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressDialog.setTitleText("Tunggu....");
                    progressDialog.show();

                    //Memanggil Method untuk mendapat nilai dari edit Text
                        getValueEditText();
                    //End

                    //Membuat String Request Dengan Metode Post
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Selamat Anda Sudah Terdaftar",Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_LONG).show();
                        }
                    }

                    ){
                        @Override
                        protected Map<String, String> getParams() {
                            //Creating Map String Params
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("nama",nama);
                            params.put("alamat",alamat);
                            params.put("no_hp",no);
                            params.put("email",email);
                            params.put("password",pass);

                            return params;
                        }
                    };
                    //Membuat Request Queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    //Menambahkan StringRequst Object ke dalam Request Queue
                    requestQueue.add(stringRequest);

                    et_Nama.setText("");
                    et_Alamat.setText("");
                    et_No.setText("");
                    et_Email.setText("");
                    et_Password.setText("");

                    et_Nama.setFocusable(true);
                }
            });

    }

    public void getValueEditText(){
        nama = et_Nama.getText().toString().trim();
        alamat = et_Alamat.getText().toString().trim();
        no = et_No.getText().toString().trim();
        email = et_Email.getText().toString().trim();
        pass = et_Password.getText().toString().trim();
    }
}
