package at.ac.univie.hci.typo.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random.*;

import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.Random;

import at.ac.univie.hci.typo.Controller.ActivityManagement.Activities;
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
    private Button startGame;
    private TextView warningGame;
    private TextView timerTextView;
    private WordsManager wordsManager;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(at.ac.univie.hci.typo.R.layout.activity_game);

        initializeAllVariables();
        startTrackingActivities();



        /**
         * Reciever for Activity Recognition
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
        backButton = (ImageButton) findViewById(R.id.buttonBack2);
        sController = new StatisticsController();
        wordsManager = new WordsManager();
        //Two arraylists to compare and compute statistics in StatisticsManager
        correctWords = new ArrayList<String>();
        incorrectWords = new ArrayList<String>();
        words= new Words();
        score = (TextView) findViewById(R.id.textViewScore);
        score.setText(String.valueOf("SCORE: "+scoreCounter));
        wordsCounter = 0;
        scoreCounter = 0;
        score.setVisibility(View.INVISIBLE);


        timerTextView = (TextView) findViewById(R.id.textViewTimer);
        timerTextView.setText("00:60");
        //TODO change time to 60 sec
        final CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished/1000 < 10) {
                    timerTextView.setTextColor(Color.RED);
                    timerTextView.setText("00:0" +String.valueOf(millisUntilFinished / 1000));
                }
                else {
                    timerTextView.setText("00:" +String.valueOf(millisUntilFinished / 1000));}
            }

            public void onFinish() {
                timerTextView.setText("End!");
                endGame();

            }
        };

        txtActivity = (TextView) findViewById(R.id.txt_activity);
        txtActivity.setVisibility(View.INVISIBLE);
        txtConfidence = (TextView) findViewById(R.id.txt_confidence);
//        btnStopTracking = (Button) findViewById(R.id.btn_stop_tracking);
        //List of all activities during the game
        activitiesList = new ArrayList<String>();
        startGame = (Button) findViewById(R.id.buttonStartGame);
        warningGame = (TextView) findViewById(R.id.textViewWarning);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtActivity.setVisibility(View.VISIBLE);
                score.setVisibility(View.VISIBLE);
                warningGame.setVisibility(View.INVISIBLE);
                startGame.setVisibility(View.INVISIBLE);
                setFocusOnEditText();
                countDownTimer.start();
            }
        });
    }

    /**
     * Method sets focus on the field where the words should be entered
     */
    public void setFocusOnEditText() {

        wordToTypeIn = (EditText) findViewById(R.id.editTextWordToTypeIn);
        wordToTypeIn.addTextChangedListener(this);
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

                label = Activities.IN_VEHICLE_ACTIVITY;
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                label = Activities.ON_BICYCLE_ACTIVITY;
                break;
            }
            case DetectedActivity.RUNNING: {
                label = Activities.RUNNING_ACTIVITY;

                break;
            }
            case DetectedActivity.STILL: {

                label = Activities.STILL_ACTIVITY;
                break;
            }
            case DetectedActivity.TILTING: {

                label = Activities.TILTING_ACTIVITY;

                break;
            }
            case DetectedActivity.WALKING: {

                label = Activities.WALKING_ACTIVITY;

                break;
            }
            case DetectedActivity.UNKNOWN: {

                label ="Activity Unknown";
                break;
            }
        }
        Log.e(TAG, "User activity: " + label + ", Confidence: " + confidence);

        if (confidence > ConstantsForActivities.CONFIDENCE) {
            getBonusPoints(label);
            activitiesList.add(label);
            txtActivity.setText(activitiesList.get(activitiesList.size()-1).toString());
            txtConfidence.setText("Confidence: " + confidence);
            System.out.println(label + ": " + confidence);
        }
    }


    public void getBonusPoints(String activity) {
        switch (activity) {
            case Activities.STILL_ACTIVITY:
                break;
            case Activities.IN_VEHICLE_ACTIVITY:
                scoreCounter += (int) (scoreCounter * 0.5);
                break;
            case Activities.RUNNING_ACTIVITY:
                scoreCounter += (int) (scoreCounter * 1.0);
                break;
            case Activities.TILTING_ACTIVITY:
                scoreCounter += (int) (scoreCounter * 0.1);
                break;
            case Activities.WALKING_ACTIVITY:
                scoreCounter += (int) (scoreCounter * 0.3);
                break;
            default:
                break;
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
            sController.checkMissedKeys(initialWord, typedIn);
            wordToTypeIn.setText("");
            correctWords.add(initialWord);
            incorrectWords.add(typedIn);
            if (typedIn.equalsIgnoreCase(initialWord)) {

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
     * End a game and compute and get all Statistics
     */
    private void endGame() {
        System.out.println("ARRAY OF CONTEXT BY THE END OF THE GAME: " + activitiesList.toString());
        String context = sController.getContextOfAGame(activitiesList);
        Double accuracy = sController.computeAccuracy(correctWords, incorrectWords);
        String time = sController.getTimeOfTheGame();
        int score = scoreCounter;
        String mostMissedKey = sController.getMostMissedKeyOfTheGame();
        int keysPerMinute = wordsManager.countKeysOfAllWords(incorrectWords);
        stopTracking();
        Intent intent = new Intent(GameActivity.this, AfterGameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("mostMissedKey", mostMissedKey);
        bundle.putDouble("accuracy", accuracy);
        bundle.putString("time", time);
        bundle.putInt("score", score);
        bundle.putInt("keysPerMinute", keysPerMinute);

        if (context.equalsIgnoreCase(Activities.IN_VEHICLE_ACTIVITY)) {
            //TODO new activity with buttons with different transports and save stats there
            Intent otherIntent = new Intent(GameActivity.this, TransportAskingActivity.class);
            intent.putExtras(bundle);
            startActivity(otherIntent);
        }

        bundle.putString("context", context);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void openMainActivityFromGame(View view) {
        stopTracking();
        startActivity(new Intent(this, MainActivity.class));
    }
}
