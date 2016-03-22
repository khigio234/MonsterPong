package com.demo01.pdkpro.bong_bay.ScreenControl;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;
import com.demo01.pdkpro.bong_bay.SoundConttrol.SoundClickButton;

public class MainControl extends AppCompatActivity {

    private Intent selectMonsterScreen,introduceScreen,highScoreScreen;
    private Button btnPlay,btnIntoduce,btnHighScore,btnSound,btnMusic;
    private SoundClickButton soundClickButton;
    private MediaPlayer backGroundMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_control);

        defineNextScreen();
        defineSound();

        defineButtonToNextScreen(btnPlay, R.id.btnPlay, R.drawable.btn_play, R.drawable.btn_playpress, selectMonsterScreen);
        defineButtonToNextScreen(btnHighScore, R.id.btnHighScore, R.drawable.btn_highscore, R.drawable.btn_highscorepress, highScoreScreen);
        defineButtonToNextScreen(btnIntoduce, R.id.btnIntroduce, R.drawable.btn_info, R.drawable.btn_infopress, introduceScreen);

        btnSound = (Button) findViewById(R.id.btnSound);
        controlClickButtonSound();

        btnMusic = (Button) findViewById(R.id.btnMusic);
        controlBackgroundMusic();

    }
    private void defineSound(){
        backGroundMusic = MediaPlayer.create(MainControl.this,R.raw.background_music);
        backGroundMusic.setLooping(true);
        backGroundMusic.start();

        soundClickButton =  new SoundClickButton(this.getBaseContext());
    }

    private void defineNextScreen(){
        selectMonsterScreen = new Intent(MainControl.this,SelectMonsterScreen.class);
        introduceScreen = new Intent(MainControl.this,IntroduceScreen.class);
        highScoreScreen = new Intent(MainControl.this,HighScoreScreen.class);
    }

    private void defineButtonToNextScreen(Button btn, int id, int btnBackground, int btnBacgroundPress, Intent nextScreen){
        btn = (Button) findViewById(id);
        handleClickButton(btn, btnBackground, btnBacgroundPress, nextScreen);
    }

    private void handleClickButton(final Button button, final int btnBackground, final int btnBacgroundPress, final Intent nextScreen){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                soundClickButton.startSong();
                processingClickButtonMoveScreen(button, event, btnBackground, btnBacgroundPress, nextScreen);
                return false;
            }
        });
    }

    private final void processingClickButtonMoveScreen(Button button,MotionEvent event,int btnBackground,int btnBacgroundPress,Intent nextScreen){
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

    private void controlClickButtonSound(){
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.soundStatus) {
                    btnSound.setBackgroundResource(R.drawable.btn_soundban);
                } else {
                    btnSound.setBackgroundResource(R.drawable.btn_sound);
                }
                Constants.soundStatus = !Constants.soundStatus;
                soundClickButton.startSong();
            }
        });
    }

    private void controlBackgroundMusic(){
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.musicStatus){
                    btnMusic.setBackgroundResource(R.drawable.btn_musicban);
                    backGroundMusic.pause();
                }else{
                    btnMusic.setBackgroundResource(R.drawable.btn_music);
                    backGroundMusic.start();
                }
                soundClickButton.startSong();
                Constants.musicStatus = !Constants.musicStatus;
            }
        });
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
    }
}
