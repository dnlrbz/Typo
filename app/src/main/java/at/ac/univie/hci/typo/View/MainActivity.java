package at.ac.univie.hci.typo.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

import at.ac.univie.hci.typo.Controller.ActivityManagement.BackgroundActivityService;
import at.ac.univie.hci.typo.Controller.ActivityManagement.ConstantsForActivities;
import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.R;

public class MainActivity extends AppCompatActivity {


    private Button toGame;
    private TextView maxScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatisticsController sController = new StatisticsController();
        toGame = (Button) findViewById(R.id.playButton);
        maxScore = (TextView) findViewById(R.id.textViewMaxScore);
        maxScore.setText("HIGHSCORE: " + sController.getHighScore());


        //Database.mPlayerDAO.addPlayer(new Player("Vasya"));
        //Database.mGameStatsDAO.addGameStatistics(new GameStatistics(new Player("Vasya"), 1, 89.4, 201, "P", "Bus", "22:00", 1, 100));
        System.out.println("**********" + Database.mGameStatsDAO.getAllGameStatistics().toString());

        //Database.mPlayerDAO.addPlayer(new Player("Sasha"));
        //Database.mGameStatsDAO.addGameStatistics(new GameStatistics(new Player("Sasha"), 3, 90.1, 199, "S", "Walking", "11:42", 2, 78));
        //Database.mGameStatsDAO.addGameStatistics(new GameStatistics(new Player("Sasha"), 1, 90.1, 199, "S", "Walking", "11:42", 3, 78));

        //Database.mGameStatsDAO.addGameStatistics(new GameStatistics(new Player("Sasha"), 2, 90.1, 199, "S", "Walking", "11:42", 4, 78));

    }

    public void openGameActivity(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }

    public void openScoresActivity(View view) {
        startActivity(new Intent(this, ScoresActivity.class));
    }

    public void openAboutActivity(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }


}
