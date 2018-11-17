package at.ac.univie.hci.typo.View.ListAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.R;

public class NamesListAdapter extends ArrayAdapter<String> {
    Context context;
    int ressource;
    List<String> list;

    public NamesListAdapter(Context context, int ressource, List<String> list) {
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


        TextView textViewName = (TextView) view.findViewById(R.id.textViewNameToChoose);


        String name = list.get(position);


        textViewName.setText(name);

        return view;
    }
}
