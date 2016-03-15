package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.demo01.pdkpro.bong_bay.R;

/**
 * Created by CO on 1/22/2016.
 */
public class Player extends ObjectFather{
    int songFile;
    SoundManager mSoundManager;

    public Player(int sizeX,int sizeY,int Image, float x, float y, Context context){
        super(sizeX,sizeY,Image,x,y,context);
    }
    public void update(MotionEvent event, View view){
        float touchX = event.getX();
        float touchY = event.getY();

        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_MOVE:
                if (touchX + this.getSizeX()/2 >= view.getWidth()) {
                    touchX = view.getWidth() - this.getSizeX()/2;
                }
                this.setX(touchX-this.getSizeX()/2 < 0 ? 0:touchX-this.getSizeX()/2);
                break;
            case MotionEvent.ACTION_DOWN:
                if(this.getX() < touchX)
                    this.setX(this.getX() + 20 > view.getWidth() ? view.getWidth() - this.getSizeX(): this.getX() + 20);
                if(this.getX() > touchX)
                    this.setX(this.getX() - 20<0? 0:this.getX()-20);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }
    //taoj nhac
    public void setSong(Context context){
        mSoundManager = new SoundManager();
        mSoundManager.initSounds(context);
        mSoundManager.addSound(0, R.raw.chamgach);
    }
    // phát nhạc
    public void PlaySong(){
        this.mSoundManager.playSound(0);
    }
}
