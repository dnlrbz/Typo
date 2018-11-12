package at.ac.univie.hci.typo.View.ListAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.R;

public class GameStatsListAdapter extends ArrayAdapter<GameStatistics> {

    Context context;
    int ressource;
    List<GameStatistics> list;

    public GameStatsListAdapter(Context context, int ressource, List<GameStatistics> list) {
        super(context, ressource, list);

        this.context = context;
        this.list = list;
        this.ressource = ressource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(ressource, null);

        TextView textViewGameNumber = (TextView) view.findViewById(R.id.textViewGameNumber);
        TextView textViewAccuracy = (TextView) view.findViewById(R.id.textViewAccuracy3);

        TextView textViewKPM = (TextView) view.findViewById(R.id.textViewKPM2);
        TextView textViewContext = (TextView) view.findViewById(R.id.textViewContext);
        TextView textViewMostMissedKey = (TextView) view.findViewById(R.id.textViewMostMissedKey);
        TextView textViewTime = (TextView) view.findViewById(R.id.textViewTime);

        GameStatistics gameStatistics = list.get(position);

        textViewGameNumber.setText(String.valueOf(gameStatistics.getGameCounter()));
        textViewAccuracy.setText(String.valueOf(gameStatistics.getAccuracy()) + " %");
        textViewKPM.setText(String.valueOf(gameStatistics.getKeysPerMinute()) + " KPM");
        textViewContext.setText(gameStatistics.getContext());
        textViewMostMissedKey.setText(gameStatistics.getMostMissedKey());
        textViewTime.setText(gameStatistics.getTimeOfTheGame());



        return view;
    }
}
