package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //變數宣告
    EditText tab2et_up,tab2et_down,tab2et_right,tab2et_left;
    TextView tab2tv_up_show,tab2tv_down_show,tab2tv_right_show,tab2tv_left_show;
    TextClock textclock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}