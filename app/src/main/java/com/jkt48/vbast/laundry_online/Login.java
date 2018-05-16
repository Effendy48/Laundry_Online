package com.jkt48.vbast.laundry_online;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Util.Server;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {

    TextView txt_title_login,etdaftar;

    int success;
    ConnectivityManager conMgr;
    private String url = Server.URL+"login_member.php";
    private static final String TAG = Login.class.getSimpleName();
    private static final String TAG_SUCCESS = "sucess";
    private static final String TAG_MESSAGE = "message";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_ID = "kd";

    String TAG_JSON_OBJECT = "json_obj_req";

    SharedPreferences sharedPreferences;
    Boolean session = false;
    String kd,email;

    //RequestQueue requestQueue;

    Intent intent;

    public static final String my_shared_preference = "my_shared_prefrence";
    public static final String session_status = "session_status";

    SweetAlertDialog sweetAlertDialog;
    EditText et_Email,et_Password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();


        //Cek Session Login Jika True Maka Langsung Buka Main Activity
        sharedPreferences = getSharedPreferences(my_shared_preference,Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status,false);

        kd = sharedPreferences.getString(TAG_ID,null);
        email = sharedPreferences.getString(TAG_EMAIL,null);

        if(session){
            /*NotificationCompat.Builder notificationCompat = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext()).
                    setSmallIcon(R.drawable.clothes).setContentTitle(email).setAutoCancel(true).setContentText("Selamat Datang");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,notificationCompat.build());
*/
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra(TAG_ID,kd);
            intent.putExtra(TAG_EMAIL,email);
            finish();
            startActivity(intent);
        }


        //Typeface
        Typeface lobster = Typeface.createFromAsset(getAssets(),"Lobster.ttf");
        //Initial Edit Text
        et_Email = (EditText)findViewById(R.id.et_email);
        et_Password = (EditText)findViewById(R.id.et_password);

        //Initial Button
        btnLogin = (Button)findViewById(R.id.btn_masuk);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_Email.getText().toString().trim();
                String password = et_Password.getText().toString().trim();

                //Check

                if(email.trim().length() > 0 && password.trim().length() > 0){

                        checkLogin(email,password);
                    }else{
                        Toast.makeText(Login.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                }

        });

        //Initial TextView
        txt_title_login = (TextView)findViewById(R.id.txt_title_login);
        etdaftar = (TextView)findViewById(R.id.et_daftar);
        etdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Daftar.class));
            }
        });

        txt_title_login.setTypeface(lobster);
    }

    //Method CheckLogin
    private void checkLogin(final String email,final String password){
        sweetAlertDialog = new SweetAlertDialog(this);
        sweetAlertDialog.setTitleText("Logging in ...");
        sweetAlertDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response : " + response.toString());
                sweetAlertDialog.dismiss();

                try{
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    //Check Error node in json

                    if(success == 1){
                        String uname = jObj.getString(TAG_EMAIL);
                        String kd = jObj.getString(TAG_ID);

                        Log.e("Succesfully Login ", jObj.toString());

                        Toast.makeText(getApplicationContext(),jObj.getString(TAG_MESSAGE),Toast.LENGTH_SHORT).show();
                        //Menyimpan Login Ke Session
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(session_status,true);
                        editor.putString(TAG_ID,kd);
                        editor.putString(TAG_EMAIL,uname);
                        editor.commit();

                        intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra(TAG_ID,kd);
                        intent.putExtra(TAG_EMAIL,uname);
                        finish();
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),jObj.getString(TAG_MESSAGE),Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Login Error"+error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                sweetAlertDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();

                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, TAG_JSON_OBJECT);
    }
}
