package com.demo01.pdkpro.bong_bay.ScreenControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo01.pdkpro.bong_bay.GamePlay.MainGame;

public class GamePlayScreen extends AppCompatActivity {

    private Intent resultScreen ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainGame(this));
        resultScreen = new Intent(GamePlayScreen.this,ResultScreen.class);
    }
    public void resultScreen(){
        startActivity(resultScreen);
    }
}
