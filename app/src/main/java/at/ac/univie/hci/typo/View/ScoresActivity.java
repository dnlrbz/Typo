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

import org.w3c.dom.Text;

import java.util.List;

import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.R;
import at.ac.univie.hci.typo.View.ListAdapters.ScoreListViewAdapter;

public class ScoresActivity extends AppCompatActivity {


    List<Score> scoreList;
    ListView listViewScores;
    ImageButton buttonBack;
    ImageButton deleteButton;
    ScoreListViewAdapter scoreListViewAdapter;
    TextView hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        StatisticsController statisticsController = new StatisticsController();
        buttonBack = (ImageButton) findViewById(R.id.buttonBack);
        deleteButton = (ImageButton) findViewById(R.id.imageButtonDelete);
        hint = (TextView) findViewById(R.id.textViewHint);
        hint.setText(R.string.hint_to_tap_name);
        hint.setTextColor(getResources().getColor(R.color.orange));
        scoreList = statisticsController.getScoresList();

        scoreListViewAdapter = new
                ScoreListViewAdapter(this, R.layout.names_list_item, scoreList);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint.setText(R.string.hint_to_delete_item);
                hint.setTextColor(Color.RED);
                setDeleteListView();
            }
        });








        listViewScores = (ListView) findViewById(R.id.listViewScores);



        listViewScores.setAdapter(scoreListViewAdapter);

        setNormalListView();


    }

    public void openMainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }


    public void setNormalListView() {
        listViewScores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hint.setText(getResources().getString(R.string.hint_to_tap_name));
                hint.setTextColor(getResources().getColor(R.color.orange));
                Intent intent = new Intent(view.getContext(), StatisticsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("playerName", scoreList.get(position).getPlayer().getName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void setDeleteListView() {
        listViewScores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Database.mPlayerDAO.deletePlayerByName(scoreList.get(position).getPlayer().getName());
                scoreListViewAdapter.remove(scoreList.get(position));
                scoreListViewAdapter.notifyDataSetChanged();
                setNormalListView();
            }
        });
    }




}

