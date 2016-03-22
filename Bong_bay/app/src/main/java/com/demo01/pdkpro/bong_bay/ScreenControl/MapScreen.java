package com.demo01.pdkpro.bong_bay.ScreenControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;

public class MapScreen extends AppCompatActivity {

    private Intent gamePlayScreen;
    private ImageView mapLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        mapLevel = (ImageView) findViewById(R.id.mapLevel);
        mapLevel.setImageResource(Constants.ARR_MAP.get(Constants.level));
        gamePlayScreen = new Intent(MapScreen.this,GamePlayScreen.class);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity(gamePlayScreen);
        return false;
    }
}
