package at.ac.univie.hci.typo.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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
        private TextView hintSpace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(at.ac.univie.hci.typo.R.layout.activity_game);

        initializeAllVariables();




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
        startTrackingActivities();



    }



    /**
     * Simple method to initialize all variables, to increase the readability of code
     */
    private void initializeAllVariables() {
        backButton = (ImageButton) findViewById(R.id.buttonBack2);
        sController = new StatisticsController();
        wordsManager = new WordsManager();
        hintSpace = (TextView) findViewById(R.id.textViewSpaceHint);
        hintSpace.setVisibility(View.INVISIBLE);
        hintSpace.setText("hit space for next word");
        //Two arraylists to compare and compute statistics in StatisticsManager
        correctWords = new ArrayList<String>();
        incorrectWords = new ArrayList<String>();
        words= new Words();
        score = (TextView) findViewById(R.id.textViewScore);
        score.setText(String.valueOf("SCORE: "+scoreCounter));
        wordsCounter = WordsManager.getRandomNumberInRange(0, 999);
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
        if (confidence > ConstantsForActivities.CONFIDENCE && type!=DetectedActivity.UNKNOWN) {
            String label = "";
            // int icon = R.drawable.ic_still;

            switch (type) {
                case DetectedActivity.IN_VEHICLE: {

                    label = ConstantsForActivities.IN_VEHICLE_ACTIVITY;
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    label = ConstantsForActivities.ON_BICYCLE_ACTIVITY;
                    break;
                }
                case DetectedActivity.RUNNING: {
                    label = ConstantsForActivities.RUNNING_ACTIVITY;

                    break;
                }
                case DetectedActivity.STILL: {

                    label = ConstantsForActivities.STILL_ACTIVITY;
                    break;
                }
                case DetectedActivity.TILTING: {

                    label = ConstantsForActivities.TILTING_ACTIVITY;

                    break;
                }
                case DetectedActivity.WALKING: {

                    label = ConstantsForActivities.WALKING_ACTIVITY;

                    break;
                }
                case DetectedActivity.UNKNOWN: {

                    //label ="Activity Unknown";
                    break;
                }
            }
            Log.e(TAG, "User activity: " + label + ", Confidence: " + confidence);

            //if (confidence > ConstantsForActivities.CONFIDENCE) {
                getBonusPoints(label);
                activitiesList.add(label);
                txtActivity.setText(activitiesList.get(activitiesList.size() - 1).toString());

                System.out.println(label + ": " + confidence);
            //}
        }
    }


    public void getBonusPoints(String activity) {

        switch (activity) {
            case ConstantsForActivities.STILL_ACTIVITY:
                scoreCounter =  scoreCounter + 1;
                txtConfidence.setText("bonus for "+ activity + " + 1");
                break;
            case ConstantsForActivities.IN_VEHICLE_ACTIVITY:
                scoreCounter =scoreCounter*3;
                txtConfidence.setText("bonus for "+ activity + " x3");
                break;
            case ConstantsForActivities.RUNNING_ACTIVITY:
                scoreCounter = scoreCounter*2;
                txtConfidence.setText("bonus for "+ activity + " x2");
                break;
            case ConstantsForActivities.TILTING_ACTIVITY:
                scoreCounter = (int) (scoreCounter * 1.2);
                txtConfidence.setText("bonus for "+ activity + "+20%");
                break;
            case ConstantsForActivities.WALKING_ACTIVITY:
                scoreCounter = scoreCounter + (int)(scoreCounter * 0.3);
                txtConfidence.setText("bonus for "+ activity + " +30%");
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
        hintSpace.setVisibility(View.INVISIBLE);
        String initialWord = gameWord.getText().toString();
        String typedIn = "";

        setColoringForKeys(initialWord, typedWord.toString().replaceAll("\\s+", ""));

            //SHOW HINT TO PRESS SPACE if score is less then 4:
            if (typedWord.toString().length() == initialWord.length() && scoreCounter < 4) {
                hintSpace.setVisibility(View.VISIBLE);
            }

            if (typedWord.toString().contains(" ")) {
                wordToTypeIn.setText("");


                if (typedWord.toString().replaceAll("\\s+", "").length() >= initialWord.length()) {
                    // if word is complete then compare and increment score if needed
                    typedIn = typedWord.toString().replaceAll("\\s+", "").
                            substring(0, initialWord.length());

                    System.out.println("CHECK TYPED IN AFtER TRANSFORMATION: " +typedIn);
                    if (typedIn.equalsIgnoreCase(initialWord)) {
                        System.out.println("INCREMENTED SCORE");
                        scoreCounter++;
                        score.setText(String.valueOf("SCORE: " + scoreCounter));
                    }
                    correctWords.add(initialWord);
                    incorrectWords.add(typedIn);
                    sController.checkMissedKeys(initialWord, typedIn);

                }

                wordsCounter = WordsManager.getRandomNumberInRange(0, 999);
                gameWord.setText(words.getList().get(wordsCounter));

            }


    }

    /**
     * Sets green for correct key and red for incorrect
     * @param initialWord
     * @param typedIn
     */
    public void setColoringForKeys(String initialWord, String typedIn) {
        if (typedIn.length() > 0 &&
                typedIn.length()<= initialWord.length()) {
            SpannableString ss = new SpannableString(initialWord);
            ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);
            ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);

            if (typedIn.charAt(typedIn.length() - 1) == initialWord.charAt(typedIn.length() - 1)) {
                ss.setSpan(fcsGreen, typedIn.length() - 1, typedIn.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                gameWord.setText(ss);
            } else if (!(typedIn.charAt(typedIn.length() - 1) == initialWord.charAt(typedIn.length() - 1))) {
                ss.setSpan(fcsRed, typedIn.length() - 1, typedIn.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                gameWord.setText(ss);
            }
        }
    }



    /**
     * End a game and compute and get all Statistics
     */
    private void endGame() {

        // ************* TEMP
        //activitiesList.add(Activities.IN_VEHICLE_ACTIVITY);
        // ************* TEMP
       // System.out.println("ARRAY OF CONTEXT BY THE END OF THE GAME: " + activitiesList.toString());
        String context = sController.getContextsList(activitiesList).toString();
        System.out.println("CONTEXTS FOR VIEW: "+context);
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
        bundle.putString("context", context);
        if (sController.getContextsList(activitiesList).toLowerCase().contains(ConstantsForActivities.IN_VEHICLE_ACTIVITY.toLowerCase())) {
            System.out.println("DIRECTING TRANSPORT ACTIVITY ********");
            System.out.println("DIRECTING TRANSPORT ACTIVITY ********");
            intent = new Intent(GameActivity.this, TransportAskingActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    /**
     * Method for back button
     * @param view
     */
    public void openMainActivityFromGame(View view) {
        stopTracking();
        startActivity(new Intent(this, MainActivity.class));
    }
}
