package com.example.cheng.gademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static GoogleAnalytics analytics;
    private FirebaseAnalytics mFirebaseAnalytics;
    public static Tracker tracker;
    private Button testBtn01, testBtn02, testBtn03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testBtn01 = (Button) findViewById(R.id.btn01);
        testBtn01.setOnClickListener(this);

        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(180);

        tracker = analytics.newTracker("UA-89864426-1");
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
        tracker.setScreenName("main Screen");
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("UI_Main")
                .setAction("onCreate")
                .setLabel("主畫面")
                .build());


        //Firebase analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,getPackageName());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,"GA");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"MainActivity");
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY,"DEMO");
        bundle.putString("TestParam","Test");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);


    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this,"go",Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.btn01:
                Toast.makeText(this,"go",Toast.LENGTH_SHORT).show();
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("UI_Main")
                        .setAction("Click_Main")
                        .setLabel("主畫面")
                        .build());


                Intent intent = new Intent();
                intent.setClass(this,FirstActivity.class);
                startActivity(intent);


                break;

        }
    }

}
