package at.ac.univie.hci.typo.View;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import at.ac.univie.hci.typo.Controller.ActivityManagement.BackgroundActivityService;
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

        Intent intent = new Intent(MainActivity.this, BackgroundActivityService.class);
        stopService(intent);

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
