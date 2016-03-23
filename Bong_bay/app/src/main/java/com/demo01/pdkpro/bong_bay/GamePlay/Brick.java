package com.demo01.pdkpro.bong_bay.GamePlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;

/**
 * Created by CO on 3/15/2016.
 */
public class Brick {
    private ArrayList<Bitmap> brick;
    private ArrayList<Float> brickX ;
    private ArrayList<Float> brickY;
    private int side;
    private SoundColission souColission;
    private int countBrickRemove = 0;
    private Bitmap bomb;
    private Bitmap bombResize;

    public Bitmap getBrick(int i){
        return brick.get(i);
    }

    public float getBrickX(int i){
        return brickX.get(i);
    }

    public float getBrickY(int i){
        return brickY.get(i);
    }

    public void setBrick(){
        brick.add(bombResize);
    }

    public void setBrickX(float f){
        brickX.add(f);
    }

    public void setBrickY(float f){
        brickY.add(f);
    }

    public Brick(Context context, MainGame view){
        brick = new ArrayList<Bitmap>();
        brickX = new ArrayList<Float>();
        brickY = new ArrayList<Float>();
        side = 60;
        souColission = new SoundColission();
        souColission.initSounds(context);
        souColission.addSound(0, R.raw.collision_brick);
        bomb = BitmapFactory.decodeResource(view.getResources(), R.drawable.block3);
        bombResize = Bitmap.createScaledBitmap(bomb, (int) side, (int) side, false);

    }

    public void draw(Canvas canvas){
        for (int i=0; i<brick.size();i++){
            canvas.drawBitmap(brick.get(i),brickX.get(i), brickY.get(i),null);
        }
    }

    public void update(Ball ball) {
        for (int i = 0; i < brick.size(); i++) {

            float tempX = ball.getX()+ball.getSizeX()/2;
            float tempYBottom = ball.getY() + ball.getSizeY();

            if(isCollison(tempX,ball.getY(),brickX.get(i), brickY.get(i),side)
                    || isCollison(tempX,tempYBottom,brickX.get(i),brickY.get(i),side)){
                ball.setvelocityY(-ball.getvelocityY());
                remove(i,ball);
            }else {
                float tempY = ball.getY() + ball.getSizeY() / 2;
                float tempXLeft = ball.getX() + ball.getSizeX();
                if (isCollison(ball.getX(), tempY, brickX.get(i), brickY.get(i), side)
                        || isCollison(tempXLeft, tempY, brickX.get(i), brickY.get(i), side)) {
                    ball.setvelocityX(-ball.getvelocityX());
                    remove(i,ball);
                }
            }
        }
    }

    private boolean isCollison(float ax, float ay, float bx, float by, float bs){
        return bx < ax && ax < bx+bs && by < ay && ay < by+bs;
    }

    public void remove(int i,Ball ball){
        if(ball.isPlayerCollissionPaddle())
            increaseCountBrickRemove();
        brick.remove(i);
        brickX.remove(i);
        brickY.remove(i);
        playSong();
    }
    //taoj nhac
    public void setSong(Context context){
        souColission = new SoundColission();
        souColission.initSounds(context);
        souColission.addSound(0, R.raw.collision_brick);
    }
    // phát nhạc
    public void playSong(){
        this.souColission.playSound(0);
    }

    public void increaseCountBrickRemove(){
        this.countBrickRemove += 1;
    }
    public int getCountBrickRemove(){
        return this.countBrickRemove;
    }
}