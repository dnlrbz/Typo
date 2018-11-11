package at.ac.univie.hci.typo.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import at.ac.univie.hci.typo.R;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Bundle bundle = getIntent().getExtras();
        String playerName = bundle.getString("playerName");
        System.out.println("*******ACTIVITY STATISTICS OPENED FOR PLAYER: " + playerName);
    }
}
