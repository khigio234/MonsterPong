package com.demo01.pdkpro.bong_bay.ScreenControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;
import com.demo01.pdkpro.bong_bay.SoundConttrol.SoundClickButton;

public class SelectMonsterScreen extends AppCompatActivity {

    private float xOld;
    private ViewFlipper viewFlipper;
    private ImageView imageView;
    private Intent mapScreen;
    private Button[] btnStart = new Button[5];
    private SoundClickButton soundClickButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_monster_screen);
        Constants.init();
        soundClickButton = new SoundClickButton(this.getBaseContext());
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        mapScreen = new Intent(SelectMonsterScreen.this,MapScreen.class);

        btnStart[0] = (Button) findViewById(R.id.btnStart1);
        btnStart[1] = (Button) findViewById(R.id.btnStart2);
        btnStart[2] = (Button) findViewById(R.id.btnStart3);
        btnStart[3] = (Button) findViewById(R.id.btnStart4);
        btnStart[4] = (Button) findViewById(R.id.btnStart5);

        for (int i= 0; i< btnStart.length ; i++) {
            handleClickButtonStart(btnStart[i],i);
        }
    }

    private void handleClickButtonStart(final Button button,final int index){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        button.setBackgroundResource(R.drawable.btn_startpress);
                        break;
                    case MotionEvent.ACTION_UP:
                        button.setBackgroundResource(R.drawable.btn_start);
                        startActivity(mapScreen);
                        Constants.monsterPlayer = index;
                        break;
                }
                soundClickButton.startSong();
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xOld = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float xCurrent = event.getX();
                float yCurrent = event.getY();
                if(xCurrent > xOld || xCurrent>18 && xCurrent < 102 && yCurrent >311 && yCurrent< 376){
                    viewFlipper.setOutAnimation(this, R.anim.right_out);
                    viewFlipper.setInAnimation(this, R.anim.left_in);
                    viewFlipper.showNext();
                }else if (xCurrent < xOld || (xCurrent>661 && xCurrent < 748 && yCurrent >311 && yCurrent< 376)){
                    viewFlipper.setOutAnimation(this,R.anim.left_out);
                    viewFlipper.setInAnimation(this, R.anim.right_in);
                    viewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }
}
