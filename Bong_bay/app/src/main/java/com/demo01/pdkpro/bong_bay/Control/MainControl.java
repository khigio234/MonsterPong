package com.demo01.pdkpro.bong_bay.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainControl extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private float xOld;
    private Intent selectMonsterScreen,introduceScreen,highScoreScreen;
    private Button btnPlay,btIntoduce,btHighScore,btnSound,btnMusic;
    HashMap<Integer,ArrayList<Integer>> hashBacground; //chua giao dien cac component
    ArrayList<Integer> arrMenu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_control);

        selectMonsterScreen = new Intent(MainControl.this,SelectMonsterScreen.class);
        introduceScreen = new Intent(MainControl.this,IntroduceScreen.class);
        highScoreScreen = new Intent(MainControl.this,HighScoreScreen.class);

        //kích vào nut start
        btnPlay = (Button) findViewById(R.id.btnPlay);
        handleClickButton(btnPlay, R.drawable.btn_play, R.drawable.btn_playpress, selectMonsterScreen);

        //kích vào nút introduce
        btIntoduce = (Button) findViewById(R.id.btIntroduce);
        handleClickButton(btIntoduce, R.drawable.btn_info, R.drawable.btn_infopress, introduceScreen);

        //kích vào nút highScore
        btHighScore = (Button) findViewById(R.id.btnHighScore);
        handleClickButton(btHighScore, R.drawable.btn_highscore, R.drawable.btn_highscorepress, highScoreScreen);

        //kich vao nut sound
        btnSound = (Button) findViewById(R.id.btnSound);
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.soundStatus) {
                    btnSound.setBackgroundResource(R.drawable.btn_sound_stop);
                } else {
                    btnSound.setBackgroundResource(R.drawable.btn_sound);
                }
                Constants.soundStatus = !Constants.soundStatus;
            }
        });

        //kich vao nut sound
        btnMusic = (Button) findViewById(R.id.btnMusic);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.musicStatus){
                    btnMusic.setBackgroundResource(R.drawable.btn_music_stop);
                }else{
                    btnMusic.setBackgroundResource(R.drawable.btn_music);
                }
                Constants.musicStatus = !Constants.musicStatus;
            }
        });

    }

    private void handleClickButton(final Button button, final int btnBackground, final int btnBacgroundPress, final Intent nextScreen){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                processerClickButtonMoveScreen(button, event, btnBackground, btnBacgroundPress, nextScreen);
                return false;
            }
        });
    }



    private final void processerClickButtonMoveScreen(Button button,MotionEvent event,int btnBackground,int btnBacgroundPress,Intent nextScreen){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                button.setBackgroundResource(btnBacgroundPress);
                break;
            case MotionEvent.ACTION_UP:
                button.setBackgroundResource(btnBackground);
                startActivity(nextScreen);
                break;
        }
    }

    private final void processerClickButtonNoMoveScreen(Button button,MotionEvent event,int btnBackground,int btnBacgroundPress,Intent nextScreen){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                button.setBackgroundResource(btnBackground);
                startActivity(nextScreen);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return  true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //this.mainView.
    }
/*
    private void createHashBackgroundAndIconGame(){
        this.hashBacground = new HashMap<>();
        //man 1
        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(R.drawable.map1);
        arr1.add(R.drawable.ball1);
        arr1.add(R.drawable.block1);
        hashBacground.put(R.drawable.monster01,arr1);

        //man 2
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(R.drawable.map2);
        arr2.add(R.drawable.ball2);
        arr2.add(R.drawable.block1);
        hashBacground.put(R.drawable.menu02,arr2);

        //man 3
        ArrayList<Integer> arr3 = new ArrayList<>();
        arr3.add(R.drawable.map3);
        arr3.add(R.drawable.ball3);
        arr3.add(R.drawable.block2);
        hashBacground.put(R.drawable.menu03,arr3);

        //man 4
        ArrayList<Integer> arr4 = new ArrayList<>();
        arr4.add(R.drawable.map4);
        arr4.add(R.drawable.ball4);
        arr4.add(R.drawable.block2);
        hashBacground.put(R.drawable.menu04,arr4);

        //man 5
        ArrayList<Integer> arr5 = new ArrayList<>();
        arr5.add(R.drawable.map5);
        arr5.add(R.drawable.ball5);
        arr5.add(R.drawable.block3);
        hashBacground.put(R.drawable.menu05,arr5);
    }
    private void createArrMenuBackground(){
        arrMenu = new ArrayList<>();
        arrMenu.add(R.drawable.menu01);
        arrMenu.add(R.drawable.menu02);
        arrMenu.add(R.drawable.menu03);
        arrMenu.add(R.drawable.menu04);
        arrMenu.add(R.drawable.menu05);
    }
    */
}
