package com.example.beaconstreamingsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;
import java.util.UUID;

/**
 * Created by ncl on 2016-11-15.
 */

public class beaconActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private Region region;

    private TextView tvId;
    int flag;

    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        tvId = (TextView) findViewById(R.id.tvId);

        flag = 0;
        beaconManager = new BeaconManager(this);

        // add this below:
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Beacon nearestBeacon = list.get(0);
                    Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi());
                    tvId.setText(nearestBeacon.getRssi() + "");

                    if(!isConnected && nearestBeacon.getRssi() > -80 ){
                        isConnected = true;

//                        AlertDialog.Builder dialog = new AlertDialog.Builder(beaconActivity.this);
//                        dialog.setTitle("알림")
//                                .setMessage("동영상이 감지되었습니다! 확인을 누르시면 다음으로 넘어갑니다")
//                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int which) {
//                                        Intent intent = new Intent(getApplicationContext(), streamingActivity.class);
//                                        startActivity(intent);
//
//                                    }
//                                }).create().show();

                        Intent intent = new Intent(getApplicationContext(), streamingActivity.class);
                         startActivity(intent);

                    }
                    else if(nearestBeacon.getRssi() < -90){
                        flag = 0;
                        Toast.makeText(beaconActivity.this, "연결종료", Toast.LENGTH_SHORT).show();
                        isConnected = false;
                    }
//                    else {
//                        Toast.makeText(beaconActivity.this, "연결종료", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });

        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 3905, 17974); // 본인이 연결할 Beacon의 ID와 Major / Minor Code를 알아야 한다.

    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);


        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
//        beaconManager.stopRanging(region);
        super.onPause();
    }


}
