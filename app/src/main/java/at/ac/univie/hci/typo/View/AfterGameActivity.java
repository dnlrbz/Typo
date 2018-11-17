package at.ac.univie.hci.typo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.R;

public class AfterGameActivity extends AppCompatActivity {
    private EditText editTextName;
    private Button saveButton;
    private ListView namesListView;
    private String playerName;
    private StatisticsController sController;
    private String mostMissedKey;
    private Double accuracy;
    private String time;
    private int score;
    private int keysPerMinute;
    private String context;
    private TextView endScoreTextView;
    private int statsId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game);
        initializeVaribles();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName = editTextName.getText().toString().replaceAll("\\s+","");
                int id = Database.mGameStatsDAO.getAllGameStatistics().size();
                if (playerName.length() != 0) {
                    int gameCounter = sController.getGameCounter(new Player(playerName));

                    if (sController.playerExists(new Player(playerName))) {

                        GameStatistics gameStatistics = new GameStatistics(new Player(playerName), gameCounter,
                                accuracy, keysPerMinute, mostMissedKey, context, time,id, score);
                        Database.mGameStatsDAO.addGameStatistics(gameStatistics);
                    } else {
                        //TODO save stats for playerName
                        Database.mPlayerDAO.addPlayer(new Player(playerName));
                        GameStatistics gameStatistics = new GameStatistics(new Player(playerName), gameCounter,
                                accuracy, keysPerMinute, mostMissedKey, context, time,id, score);
                        Database.mGameStatsDAO.addGameStatistics(gameStatistics);
                    }
                    Intent intent = new Intent(AfterGameActivity.this, ScoresActivity.class);
                    startActivity(intent);

                }
            }
        });
    }


    private void initializeVaribles() {
        editTextName = (EditText) findViewById(R.id.editTextNameOfPlayer);
        saveButton = (Button) findViewById(R.id.saveButton);
        namesListView = (ListView) findViewById(R.id._namesList);
        sController = new StatisticsController();
        Bundle bundle = getIntent().getExtras();

        mostMissedKey = bundle.getString("mostMissedKey");
        accuracy = Double.valueOf(sController.getDecimalFormat().format(bundle.getDouble("accuracy")));
        time = bundle.getString("time");
        score = bundle.getInt("score");
        keysPerMinute = bundle.getInt("keysPerMinute");
        context = bundle.getString("context");
        endScoreTextView = (TextView) findViewById(R.id.textViewGame2);
        endScoreTextView.setText("score: " + score);
        final ArrayList<String> playerNames = new ArrayList<String>();
        for (Player player: Database.mPlayerDAO.getAllPlayers()){
            playerNames.add(player.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.aftergame_name_listitem, R.id.textViewNameToChoose,
                playerNames);

        namesListView.setAdapter(arrayAdapter);

        namesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playerName = playerNames.get(position);
                System.out.println(playerName + "SELECTED PLAYER FROM LIST");
                int gameCounter = sController.getGameCounter(new Player(playerName));
                statsId = Database.mGameStatsDAO.getAllGameStatistics().size();
                GameStatistics gameStatistics = new GameStatistics(new Player(playerName), gameCounter,
                        accuracy, keysPerMinute, mostMissedKey, context, time, statsId, score);
                Database.mGameStatsDAO.addGameStatistics(gameStatistics);
                Intent intent = new Intent(AfterGameActivity.this, ScoresActivity.class);
                startActivity(intent);
            }
        });

    }

}
