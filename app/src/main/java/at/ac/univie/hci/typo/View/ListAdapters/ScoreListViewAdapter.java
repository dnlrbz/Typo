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

import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.R;

public class ScoreListViewAdapter extends ArrayAdapter<Score> {


    Context context;
    int ressource;
    List<Score> list;

    public ScoreListViewAdapter(Context context, int ressource, List<Score> list) {
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

        TextView textViewNumber = (TextView) view.findViewById(R.id.textViewNumber);
        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        TextView textViewScore = (TextView) view.findViewById(R.id.textViewScore);

        Score score = list.get(position);

        textViewNumber.setText(String.valueOf(position+1) + ". ");
        textViewName.setText(score.getPlayer().getName());
        textViewScore.setText(String.valueOf(score.getScore()));


        return view;
    }
}
