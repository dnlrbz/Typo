package at.ac.univie.hci.typo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import at.ac.univie.hci.typo.R;

public class AboutActivity extends AppCompatActivity {
    private TextView commomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        commomInfo = (TextView) findViewById(R.id.textViewInfo);
        commomInfo.setText(R.string.common_info);

    }

    public void openMainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

}
