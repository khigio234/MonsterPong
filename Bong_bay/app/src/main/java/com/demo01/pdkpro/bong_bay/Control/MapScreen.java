package com.demo01.pdkpro.bong_bay.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.demo01.pdkpro.bong_bay.R;

public class MapScreen extends AppCompatActivity {

    private Intent gamePlayScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        gamePlayScreen = new Intent(MapScreen.this,GamePlayScreen.class);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity(gamePlayScreen);
        return false;
    }
}
