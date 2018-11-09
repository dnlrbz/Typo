package at.ac.univie.hci.typo.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

import at.ac.univie.hci.typo.Controller.ActivityManagement.BackgroundActivityService;
import at.ac.univie.hci.typo.Controller.ActivityManagement.ConstantsForActivities;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.R;

public class GameActivity extends AppCompatActivity {

    private String TAG = GameActivity.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;
    private TextView txtActivity, txtConfidence;
    private ImageView imgActivity;
    private Button btnStartTrcking, btnStopTracking;
    private ArrayList<String> activitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(at.ac.univie.hci.typo.R.layout.activity_game);

        txtActivity = (TextView) findViewById(R.id.txt_activity);
        txtConfidence = (TextView) findViewById(R.id.txt_confidence);
        imgActivity = (ImageView) findViewById(R.id.img_activity);
        btnStartTrcking = (Button) findViewById(R.id.btn_start_tracking);
        btnStopTracking = (Button) findViewById(R.id.btn_stop_tracking);
        activitiesList = new ArrayList<String>();



        btnStartTrcking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTracking();
            }
        });

        btnStopTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitiesList = new ArrayList<String>();
                txtActivity.setText(activitiesList.toString());
                stopTracking();
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ConstantsForActivities.BROADCAST_DETECTED_ACTIVITY)) {
                    int type = intent.getIntExtra("type", -1);
                    int confidence = intent.getIntExtra("confidence", 0);
                    handleUserActivity(type, confidence);
                }
            }
        };

        startTracking();



    }

    private void handleUserActivity(int type, int confidence) {
        //String label = getString(R.string.activity_unknown);
        String label = "Unknown activity";
        // int icon = R.drawable.ic_still;

        switch (type) {
            case DetectedActivity.IN_VEHICLE: {
                // label = getString(R.string.activity_in_vehicle);
                label = "IN VEHICLE";
                // icon = R.drawable.ic_driving;
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                // label = getString(R.string.activity_on_bicycle);
                label = "ON BICYLCE";
                // icon = R.drawable.ic_on_bicycle;
                break;
            }
            case DetectedActivity.ON_FOOT: {
                // label = getString(R.string.activity_on_foot);
                label = "ON FOOT";
                // icon = R.drawable.ic_walking;
                break;
            }
            case DetectedActivity.RUNNING: {
                //label = getString(R.string.activity_running);
                label = "RINNING";
                // icon = R.drawable.ic_running;
                break;
            }
            case DetectedActivity.STILL: {
                // label = getString(R.string.activity_still);
                label = "STILL";
                break;
            }
            case DetectedActivity.TILTING: {
                // label = getString(R.string.activity_tilting);
                label = "TILTING";
                // icon = R.drawable.ic_tilting;
                break;
            }
            case DetectedActivity.WALKING: {
                // label = getString(R.string.activity_walking);
                label = "WALKING";
                // icon = R.drawable.ic_walking;
                break;
            }
            case DetectedActivity.UNKNOWN: {
                //label = getString(R.string.activity_unknown);
                label ="Activity Unknown";
                break;
            }
        }
        Log.e(TAG, "User activity: " + label + ", Confidence: " + confidence);

        if (confidence > ConstantsForActivities.CONFIDENCE) {
            activitiesList.add(label);
            txtActivity.setText(activitiesList.toString());
            txtConfidence.setText("Confidence: " + confidence);
            System.out.println(label + ": " + confidence);
            // imgActivity.setImageResource(icon);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(ConstantsForActivities.BROADCAST_DETECTED_ACTIVITY));
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void startTracking() {
        Intent intent1 = new Intent(GameActivity.this, BackgroundActivityService.class);
        startService(intent1);
    }

    private void stopTracking() {
        Intent intent = new Intent(GameActivity.this, BackgroundActivityService.class);
        stopService(intent);
    }
}
