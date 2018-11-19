package at.ac.univie.hci.typo.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.R;
import at.ac.univie.hci.typo.View.ListAdapters.GameStatsListAdapter;

public class StatisticsActivity extends AppCompatActivity {

    List<GameStatistics> statisticsList;
    ListView listViewStatisctics;
    TextView textViewHeader;
    ImageButton deleteButton;
    GameStatsListAdapter statsListAdapter;
    private TextView hint;
    private boolean setToDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Bundle bundle = getIntent().getExtras();
        String playerName = bundle.getString("playerName");

        hint = (TextView) findViewById(R.id.textViewHintdelete);
        hint.setVisibility(View.INVISIBLE);


        StatisticsController sController = new StatisticsController();
        textViewHeader = (TextView) findViewById(R.id.textViewHead);
        textViewHeader.setText("STATISTICS\n" + playerName);
        textViewHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setToDelete) {
                    hint.setVisibility(View.INVISIBLE);
                    setNormalListView();
                    setToDelete = false;
                    return;
                }
                if (!setToDelete) {
                    setToDelete = true;
                    hint.setVisibility(View.VISIBLE);
                    setDeleteListView();
                    return;
                }


            }
        });

        statisticsList = sController.getStatisticsListForPlayer(playerName);

        listViewStatisctics = (ListView) findViewById(R.id.listViewStats);

        statsListAdapter = new
                GameStatsListAdapter(this, R.layout.statistics_list_item, statisticsList);

        listViewStatisctics.setAdapter(statsListAdapter);








    }

    public void openScoresActivity(View view) {
        startActivity(new Intent(this, ScoresActivity.class));
    }

    public void setNormalListView() {
        listViewStatisctics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // hint.setText(getResources().getString(R.string.hint_to_tap_name));
               // hint.setTextColor(getResources().getColor(R.color.orange));
                //hint.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void setDeleteListView() {
        listViewStatisctics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Database.mGameStatsDAO.deleteGameStatisticsById(statisticsList.get(position).getId());
                statsListAdapter.remove(statisticsList.get(position));
                statsListAdapter.notifyDataSetChanged();
                //setNormalListView();
            }
        });
    }
}
