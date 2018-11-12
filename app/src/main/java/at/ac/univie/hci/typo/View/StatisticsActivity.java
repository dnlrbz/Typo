package at.ac.univie.hci.typo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.R;
import at.ac.univie.hci.typo.View.ListAdapters.GameStatsListAdapter;

public class StatisticsActivity extends AppCompatActivity {

    List<GameStatistics> statisticsList;
    ListView listViewStatisctics;
    TextView textViewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Bundle bundle = getIntent().getExtras();
        String playerName = bundle.getString("playerName");
        StatisticsController sController = new StatisticsController();
        textViewHeader = (TextView) findViewById(R.id.textViewHead);
        textViewHeader.setText("STATISTICS\n" + playerName);

        statisticsList = sController.getStatisticsList(playerName);

        listViewStatisctics = (ListView) findViewById(R.id.listViewStats);

        GameStatsListAdapter statsListAdapter = new
                GameStatsListAdapter(this, R.layout.statistics_list_item, statisticsList);

        listViewStatisctics.setAdapter(statsListAdapter);








    }

    public void openScoresActivity(View view) {
        startActivity(new Intent(this, ScoresActivity.class));
    }
}
