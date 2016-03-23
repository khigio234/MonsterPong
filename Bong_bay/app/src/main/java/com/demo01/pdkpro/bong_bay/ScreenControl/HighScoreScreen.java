package com.demo01.pdkpro.bong_bay.ScreenControl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;

public class HighScoreScreen extends AppCompatActivity {
    private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);
        txtView = (TextView) findViewById(R.id.textView);
        txtView.setText("" + Constants.highScore);

    }

}
