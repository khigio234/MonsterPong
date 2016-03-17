package com.demo01.pdkpro.bong_bay.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo01.pdkpro.bong_bay.Game.MainGame;

public class GamePlayScreen extends AppCompatActivity {

    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainGame(this));
        intent = new Intent(GamePlayScreen.this,MapScreen.class);
    }
    public void nextScreen(){
        startActivity(intent);
    }
}
