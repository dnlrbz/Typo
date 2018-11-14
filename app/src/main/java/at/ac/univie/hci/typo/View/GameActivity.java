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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random.*;

import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.Random;

import at.ac.univie.hci.typo.Controller.ActivityManagement.BackgroundActivityService;
import at.ac.univie.hci.typo.Controller.ActivityManagement.ConstantsForActivities;
import at.ac.univie.hci.typo.Controller.StatisticsController;
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
    Words words;
    int wordsCounter;
    int scoreCounter;
    private EditText wordToTypeIn;
    private TextView score;
    ArrayList<String> correctWords;
    ArrayList<String> incorrectWords;
    StatisticsController sController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(at.ac.univie.hci.typo.R.layout.activity_game);

        initializeAllVariables();
        startTrackingActivities();
        setFocusOnEditText();


        /**
         * If an activity is recognized
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



    }

    /**
     * Simple method to initialize all variables, to increase the readability of code
     */
    private void initializeAllVariables() {
        sController = new StatisticsController();
        //Two arraylists to compare and compute statistics in StatisticsManager
        correctWords = new ArrayList<String>();
        incorrectWords = new ArrayList<String>();
        words= new Words();
        score = (TextView) findViewById(R.id.textViewScore);
        txtActivity = (TextView) findViewById(R.id.txt_activity);
        txtConfidence = (TextView) findViewById(R.id.txt_confidence);
//        btnStopTracking = (Button) findViewById(R.id.btn_stop_tracking);
        //List of all activities during the game
        activitiesList = new ArrayList<String>();
        wordsCounter = 0;
        scoreCounter = 0;
        wordToTypeIn = (EditText) findViewById(R.id.editTextWordToTypeIn);
        wordToTypeIn.addTextChangedListener(this);
        score.setText(String.valueOf("SCORE: "+scoreCounter));
    }

    /**
     * Method sets focus on the field where the words should be entered
     */
    public void setFocusOnEditText() {
        wordToTypeIn.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(wordToTypeIn, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Method handles the determined activity(context)
     * @param type of activity
     * @param confidence in percent of activity
     */
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
            txtActivity.setText(activitiesList.get(activitiesList.size()-1).toString());
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

    /**
     * Method called by start of a game to begin tracking activities
     */
    private void startTrackingActivities() {
        Intent intent1 = new Intent(GameActivity.this, BackgroundActivityService.class);
        startService(intent1);
        /**
         * Setting new Words in Text View
         */
        gameWord = (TextView) findViewById(R.id.textViewWord);
        //TODO Change this to TextWatcher
        gameWord.setText(words.getList().get(wordsCounter));

    }

    /**
     * Stop tracking activities
     */
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

    /**
     * After some text is entered in the field
     * @param typedWord
     */
    @Override
    public void afterTextChanged(Editable typedWord) {
        //Ignoring backspaces
        String typedIn = typedWord.toString().replaceAll("\\s+","");
        String initialWord = gameWord.getText().toString();
        if (typedIn.length() == initialWord.length()) {
            wordToTypeIn.setText("");
            correctWords.add(initialWord);
            incorrectWords.add(typedIn);
            if (typedIn.equalsIgnoreCase(initialWord)) {
                //TODO change to timer! now game ends by score==20
                if (scoreCounter==20) {
                    endGameComputeStatistics(new Player("VASYA"));
                }
                scoreCounter++;
                score.setText(String.valueOf("SCORE: "+scoreCounter));
            }
            wordsCounter = WordsManager.getRandomNumberInRange(0, 999);
            gameWord.setText(words.getList().get(wordsCounter));

            System.out.println("CORRECT WORDS LIST: " + correctWords.toString());
            System.out.println("INCORRECT WORDS: " + incorrectWords.toString());
        }


    }

    /**
     * End a game and compute and save all Statistics
     * @param player
     */
    private void endGameComputeStatistics(Player player) {
        GameStatistics gameStatistics = sController.
                computeAndGetWholeGameStatistics(correctWords, incorrectWords, player, scoreCounter, activitiesList);
        Database.mGameStatsDAO.addGameStatistics(gameStatistics);
    }
}
