package com.example.cheng.gademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;

/**
 * Created by cheng on 2017/1/5.
 */

public class ThirdActivity extends FirstActivity {

    private int clickcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ((Button) findViewById(R.id.btn01)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn02)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn03)).setOnClickListener(this);

        clickcount = 0;
        strFormat = ((TextView) findViewById(R.id.test_Text)).getText().toString();
        tracker.setScreenName("Third Screen");

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn01:
                sendAnalyticmessage("UI03", "Click03", "點擊測試_03");

                TextView clickResult = (TextView) findViewById(R.id.test_Text);

                clickcount += 1;
                StringBuffer sb = new StringBuffer();
                sb.append(strFormat).append("點擊次數 :").append(clickcount);

                clickResult.setText(sb.toString());

                break;

            case R.id.btn02:

                sendAnalyticmessage("UI03", "Click03", "返回頁面1");

                Intent intent1 = new Intent();
                intent1.setClass(this,FirstActivity.class);
                startActivity(intent1);
                finish();

                break;

            case R.id.btn03:

                sendAnalyticmessage("UI03", "Click03", "返回頁面2");

                Intent intent2 = new Intent();
                intent2.setClass(this,SecondActivity.class);
                startActivity(intent2);
                finish();

                break;
        }
    }
}
