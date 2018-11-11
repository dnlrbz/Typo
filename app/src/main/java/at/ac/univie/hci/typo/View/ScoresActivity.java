package at.ac.univie.hci.typo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.plus.model.people.Person;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.R;

public class ScoresActivity extends AppCompatActivity {


    List<Score> scoreList;
    ListView listViewScores;
    ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        StatisticsController statisticsController = new StatisticsController();
        buttonBack = (ImageButton) findViewById(R.id.buttonBack);






        scoreList = statisticsController.getScoresList();

        listViewScores = (ListView) findViewById(R.id.listViewScores);


        ScoreListViewAdapter scoreListViewAdapter = new
                ScoreListViewAdapter(this, R.layout.names_list_item, scoreList);

        listViewScores.setAdapter(scoreListViewAdapter);

        listViewScores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), StatisticsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("playerName", scoreList.get(position).getPlayer().getName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    public void openMainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }







}

