package com.demo01.pdkpro.bong_bay.SoundClickButton;

import android.content.Context;
import android.media.MediaPlayer;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;

/**
 * Created by pdkpro on 18/03/2016.
 */
public class SoundClickButton {
    private MediaPlayer song;
    public SoundClickButton(Context context){
        song = MediaPlayer.create(context, R.raw.sound_click);
    }
    public void startSong(){
        if(Constants.soundStatus){
            song.start();
        }
    }
    public void pauseSong(){
        song.pause();
    }
}
