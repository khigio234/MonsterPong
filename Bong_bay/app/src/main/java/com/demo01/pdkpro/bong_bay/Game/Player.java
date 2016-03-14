package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;

import com.demo01.pdkpro.bong_bay.R;

/**
 * Created by CO on 1/22/2016.
 */
public class Player extends ObjectFather{
    int songFile;
    SoundManager mSoundManager;

    public Player(int panddleSizeX,int panddleSizeY,int image){
        super(panddleSizeX,panddleSizeY,image);
    }
    public void update(Ball ball){
        if(ball.getX() > 0 && ball.getX() <= ball.getxMax()-this.getSizeX())
        {
            this.setX(ball.getX());
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
