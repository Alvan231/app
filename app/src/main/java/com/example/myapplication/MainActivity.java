package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import javax.xml.transform.ErrorListener;

public class MainActivity extends AppCompatActivity {

    //變數宣告
    EditText tab2et_up,tab2et_down,tab2et_right,tab2et_left;
    TextView tab2tv_up_show,tab2tv_down_show,tab2tv_right_show,tab2tv_left_show;
    TextView tab1_weather_tv;
    TextView tab1_phonenumber_tv;
    TextClock textclock;
    String base_url = "http://132.226.10.241:11222/";
    String weather_url = base_url + "weather/雲林縣";

    private PhoneCallListener mPhoneState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab1_weather_tv = findViewById(R.id.tab1_weather_tv);
        tab1_phonenumber_tv = findViewById(R.id.tab1_phonenumber_tv);

        mPhoneState = new PhoneCallListener();
        ((TelephonyManager) Objects.requireNonNull(getSystemService(Context.TELEPHONY_SERVICE)))
                .listen(mPhoneState, PhoneCallListener.LISTEN_CALL_STATE);



        final String REGISTER_URL = weather_url;
        Utf8StringRequest stringRequest = new Utf8StringRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //天氣顯示
                        tab1_weather_tv.setText(response);
                        Log.i("info","response : " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error","onErrorResponse");
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

        final TabHost tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        //取得前面id
        //EditText
        tab2et_up = findViewById(R.id.tab2et_up);
        tab2et_down = findViewById(R.id.tab2et_down);
        tab2et_right = findViewById(R.id.tab2et_right);
        tab2et_left = findViewById(R.id.tab2et_left);
        //TextView
        tab2tv_up_show = findViewById(R.id.tab2tv_up_show);
        tab2tv_down_show = findViewById(R.id.tab2tv_down_show);
        tab2tv_right_show = findViewById(R.id.tab2tv_right_show);
        tab2tv_left_show = findViewById(R.id.tab2tv_left_show);
        //TextClock
        textclock = (TextClock)findViewById(R.id.textclock);

        // 設置24小時顯示格式
        textclock.setFormat12Hour("yyyy-MM-dd hh:mm");


        TabHost .TabSpec TS;

        TS = tabhost.newTabSpec("");
        TS.setContent(R.id.tab1);
        TS.setIndicator("Home");
        tabhost.addTab(TS);

        TS = tabhost.newTabSpec("");
        TS.setContent(R.id.tab2);
        TS.setIndicator("Set String");
        tabhost.addTab(TS);

        TS = tabhost.newTabSpec("");
        TS.setContent(R.id.tab3);
        TS.setIndicator("about");
        tabhost.addTab(TS);

    }
    //set string
    public void Refactorbtn_onclick(View view) {
        if(!tab2et_up.getText().toString().isEmpty()) {
            tab2tv_up_show.setText(tab2et_up.getText().toString());
        }
        if(!tab2et_down.getText().toString().isEmpty()) {
            tab2tv_down_show.setText(tab2et_down.getText().toString());
        }
        if(!tab2et_right.getText().toString().isEmpty()) {
            tab2tv_right_show.setText(tab2et_right.getText().toString());
        }
        if(!tab2et_left.getText().toString().isEmpty()) {
            tab2tv_left_show.setText(tab2et_left.getText().toString());
        }

        Toast.makeText(this,"Refactor",Toast.LENGTH_SHORT).show();
    }


    //解決中文亂碼用Utf8StringRequest
    public static class Utf8StringRequest extends StringRequest {

        public Utf8StringRequest(int method, String url,
                                 Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, (Response.ErrorListener) errorListener);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data, "utf-8");
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }
    }

    public class PhoneCallListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int currentCallState, String incomingNumber) {

            if (currentCallState == TelephonyManager.CALL_STATE_IDLE) {// 空閒
//TODO
                tab1_phonenumber_tv.setText("待機");
            } else if (currentCallState == TelephonyManager.CALL_STATE_RINGING) {// 響鈴
//TODO
                tab1_phonenumber_tv.setText("來電號碼 : " + incomingNumber);
            } else if (currentCallState == TelephonyManager.CALL_STATE_OFFHOOK) {// 接聽
//TODO
            }
            super.onCallStateChanged(currentCallState, incomingNumber);
        }
    }


}