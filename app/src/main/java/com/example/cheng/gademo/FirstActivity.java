package com.example.cheng.gademo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by cheng on 2017/1/6.
 */

public class FirstActivity extends Activity implements View.OnClickListener{

    public GoogleAnalytics analytics;
    public FirebaseAnalytics mFirebaseAnalytics;
    public Tracker tracker;

    private Button testBtn01, testBtn02, testBtn03;
    private TextView clickResult;
    private int clickcount;
    public String strFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        testBtn01 = (Button) findViewById(R.id.btn01);
        testBtn01.setOnClickListener(this);
        testBtn02 = (Button) findViewById(R.id.btn02);
        testBtn02.setOnClickListener(this);
        testBtn03 = (Button) findViewById(R.id.btn03);
        testBtn03.setOnClickListener(this);
        strFormat = ((TextView) findViewById(R.id.test_Text)).getText().toString();
        clickResult = (TextView) findViewById(R.id.test_Text);
        clickcount =0;

        //Google analytics
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker("UA-89864426-1");
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
        tracker.setScreenName("First Screen");

        //Firebase analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,getPackageName());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,"GA01");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"FirstActivity");
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY,"DEMO01");
        bundle.putString("TestParam","Test01");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn01:
                sendAnalyticmessage("UI_01","Click_01","點擊測試_01");

                clickcount += 1;
                StringBuffer sb = new StringBuffer();
                sb.append(strFormat).append("點擊次數 :").append(clickcount);

                clickResult.setText(sb.toString());

                if (clickcount >= 20){
                    Intent intent = new Intent();
                    intent.setClass(this,MainActivity.class);
                    startActivity(intent);
                }

                break;

            case R.id.btn02:

                sendAnalyticmessage("UI_01","Click_01","進入頁面2");

                Intent intent1 = new Intent();
                intent1.setClass(this,SecondActivity.class);
                startActivity(intent1);
                finish();

                break;

            case R.id.btn03:

                sendAnalyticmessage("UI_01","Click_01","進入頁面3");

                Intent intent2 = new Intent();
                intent2.setClass(this,ThirdActivity.class);
                startActivity(intent2);
                finish();

                break;
        }
    }

    public void sendAnalyticmessage(String category, String action, String label){
        tracker.send(new HitBuilders.EventBuilder()
        .setCategory(category)
        .setAction(action)
        .setLabel(label)
        .build());
    }

}
