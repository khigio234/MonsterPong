package com.demo01.pdkpro.bong_bay.GamePlay;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by pdkpro on 22/03/2016.
 */
public class SoundColission {
    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;
    private AudioManager mAudioManager;
    private Context mContext;

    public SoundColission() {

    }

    public void initSounds(Context theContext) {
        mContext = theContext;
        mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mAudioManager = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
    }

    public void addSound(int Index, int SoundID) {
        mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
    }

    public void playSound(int index) {

        int streamVolume = mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume,
                1, 0, 1f);
    }
}
