package at.ac.univie.hci.typo.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random.*;

import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.Random;

import at.ac.univie.hci.typo.Controller.ActivityManagement.BackgroundActivityService;
import at.ac.univie.hci.typo.Controller.ActivityManagement.ConstantsForActivities;
import at.ac.univie.hci.typo.Controller.WordsManager;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.Model.Words;
import at.ac.univie.hci.typo.R;

public class GameActivity extends AppCompatActivity implements TextWatcher {

    private String TAG = GameActivity.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;
    private TextView txtActivity, txtConfidence;
    private ImageView imgActivity;
    private Button btnStartTrcking, btnStopTracking;
    private ArrayList<String> activitiesList;
    private TextView gameWord;
    Words words = new Words();
    int wordsCounter;
    private EditText wordToTypeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(at.ac.univie.hci.typo.R.layout.activity_game);


        txtActivity = (TextView) findViewById(R.id.txt_activity);
        txtConfidence = (TextView) findViewById(R.id.txt_confidence);
//        btnStopTracking = (Button) findViewById(R.id.btn_stop_tracking);
        activitiesList = new ArrayList<String>();


        startTracking();

        //List of 1000 Words for a game
        wordsCounter = 0;

        wordToTypeIn = (EditText) findViewById(R.id.editTextWordToTypeIn);
        wordToTypeIn.addTextChangedListener(this);

        setFocusOnEditText();







/*
        btnStartTrcking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTracking();
            }
        });
*/
/*
        btnStopTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitiesList = new ArrayList<String>();
                txtActivity.setText(activitiesList.toString());
                stopTracking();
            }
        });
        */

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

    public void setFocusOnEditText() {
        wordToTypeIn.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(wordToTypeIn, InputMethodManager.SHOW_IMPLICIT);
    }


    private void handleUserActivity(int type, int confidence) {
        //String label = getString(R.string.activity_unknown);
        String label = "Unknown activity";
        // int icon = R.drawable.ic_still;

        switch (type) {
            case DetectedActivity.IN_VEHICLE: {

                label = "IN VEHICLE";
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                label = "ON BICYLCE";
                break;
            }
            case DetectedActivity.RUNNING: {
                label = "RUNNING";

                break;
            }
            case DetectedActivity.STILL: {

                label = "STILL";
                break;
            }
            case DetectedActivity.TILTING: {

                label = "TILTING";

                break;
            }
            case DetectedActivity.WALKING: {

                label = "WALKING";

                break;
            }
            case DetectedActivity.UNKNOWN: {

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
        /**
         * Setting new Words in Text View
         */
        gameWord = (TextView) findViewById(R.id.textViewWord);
        //TODO Change this to TextWatcher
        gameWord.setText(words.getList().get(wordsCounter));

    }

    private void stopTracking() {
        Intent intent = new Intent(GameActivity.this, BackgroundActivityService.class);
        stopService(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable typedWord) {
        if (typedWord.toString().length() == gameWord.getText().length()) {
            wordsCounter = WordsManager.getRandomNumberInRange(0, 999);
            gameWord.setText(words.getList().get(wordsCounter));
            wordToTypeIn.setText("");
        }
    }
}
