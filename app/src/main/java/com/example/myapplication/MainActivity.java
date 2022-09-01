package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {

    TextClock textclock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabHost tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        textclock = (TextClock)findViewById(R.id.textclock);
        // 设置24时制显示格式
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
}