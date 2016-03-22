package com.demo01.pdkpro.bong_bay.ScreenControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;
import com.demo01.pdkpro.bong_bay.SoundConttrol.SoundClickButton;

public class ResultScreen extends AppCompatActivity {

    private Button btnReplay,btnNext;
    private ImageView imageResultBackground,imageResultMonster;
    private Intent nextScreen;
    private SoundClickButton soundClickButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        nextScreen = new Intent(ResultScreen.this,MapScreen.class);
        soundClickButton = new SoundClickButton(this.getBaseContext());
        imageResultBackground = (ImageView) findViewById(R.id.imageResultBackGround);
        imageResultMonster = (ImageView) findViewById(R.id.imageResultMonster);

        showResult();

        btnReplay = (Button) findViewById(R.id.btnReplay);
        btnNext = (Button) findViewById(R.id.btnNext);
        handleClickButton(btnNext,R.drawable.btn_next,R.drawable.btn_nextpress);
        handleClickButton(btnReplay,R.drawable.btn_replay,R.drawable.btn_replaypress);
    }

    private void showResult(){
        if(Constants.isWin){
            if(Constants.lever!=5) {
                Constants.lever = Constants.lever+1;
            }
            setImageResult(R.drawable.win,Constants.arrMonster.get(Constants.monsterPlayer));
        }else {
            setImageResult(R.drawable.lose,Constants.arrMonsterLose.get(Constants.monsterPlayer));
        }
    }

    private void handleClickButton(final Button button,final int background,final int backgroundPress){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        button.setBackgroundResource(background);
                        break;
                    case MotionEvent.ACTION_UP:
                        button.setBackgroundResource(backgroundPress);
                        startActivity(nextScreen);
                        break;
                }
                soundClickButton.startSong();
                return false;
            }
        });
    }

    private void setImageResult(int background,int monster){
        imageResultBackground.setImageResource(background);
        imageResultMonster.setImageResource(monster);
    }

}
