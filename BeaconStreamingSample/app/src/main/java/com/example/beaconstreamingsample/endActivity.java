package com.example.beaconstreamingsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kth91 on 2016-11-18.
 */

public class endActivity extends AppCompatActivity implements View.OnClickListener {

    Button replyBtn;
    Button backBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        replyBtn = (Button) findViewById(R.id.end_replay);
        backBtn = (Button) findViewById(R.id.end_back);

        replyBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.end_replay:
                Intent intent1 = new Intent(getApplicationContext(), streamingActivity.class);
                startActivity(intent1);
                break;
            case R.id.end_back:
                Intent intent2 = new Intent(getApplicationContext(), beaconActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
