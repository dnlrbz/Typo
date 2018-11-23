package at.ac.univie.hci.typo.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
        String text = getResources().getString(R.string.common_info);
        String textToHighlight1 = "sitting, walking, tilting, running or using transport";
        String textToHighlight2 = "as fast as you can";
        String textToHighlight3 = "challenge other players";
        String textToHighlight4 = "Game was made by Daniil Rabizo";


        int indexFrom1 = text.indexOf(textToHighlight1);
        int indexTo1 = indexFrom1 + textToHighlight1.length();
        int indexFrom2 = text.indexOf(textToHighlight2);
        int indexTo2 = indexFrom2 + textToHighlight2.length();
        int indexFrom3 = text.indexOf(textToHighlight3);
        int indexTo3 = indexFrom3 + textToHighlight3.length();
        int indexFrom4 = text.indexOf(textToHighlight4);
        int indexTo4 = indexFrom4 + textToHighlight4.length();

        SpannableString ss = new SpannableString(getResources().getString(R.string.common_info));
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);


        ss.setSpan(fcsBlue, indexFrom1, indexTo1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(fcsRed, indexFrom2, indexTo2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(fcsGreen, indexFrom3, indexTo3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), indexFrom4, indexTo4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        commomInfo.setText(ss);

    }

    public void openMainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

}
