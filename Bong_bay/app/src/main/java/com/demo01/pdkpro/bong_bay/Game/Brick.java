package com.demo01.pdkpro.bong_bay.Game;

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

    public Bitmap getBrick(int i){
        return brick.get(i);
    }

    public float getBrickX(int i){
        return brickX.get(i);
    }

    public float getBrickY(int i){
        return brickY.get(i);
    }

    public Brick(){
        brick = new ArrayList<Bitmap>();
        brickX = new ArrayList<Float>();
        brickY = new ArrayList<Float>();
        side = 50;
    }

    public void init(int w, int h, MainGame view) {

        Bitmap bomb = BitmapFactory.decodeResource(view.getResources(), R.drawable.block3);
        Bitmap bombResize = Bitmap.createScaledBitmap(bomb, (int) side, (int) side, false);
        for (int j = 3; j < 7; j++) {
            float i = 3;
            while ((i+3) * 50 < (w - 50)) {
                float x = i++ * 50;
                float y = 2 * h / 3 - j * 50;
                brick.add(bombResize);
                brickX.add(x);
                brickY.add(y);
            }
        }
    }

    public void draw(Canvas canvas){
        for (int i=0; i<brick.size();i++){
            canvas.drawBitmap(brick.get(i),brickX.get(i), brickY.get(i),null);
        }
    }

    public void collision(Ball ball) {
        for (int i = 0; i < brick.size(); i++) {

            float tempX = ball.getX()+ball.getSizeX()/2;
            float tempYBottom = ball.getY() + ball.getSizeY();

            if(isCollison(tempX,ball.getY(),brickX.get(i), brickY.get(i),side)
                    || isCollison(tempX,tempYBottom,brickX.get(i),brickY.get(i),side)){
                ball.setSpeedY(-ball.getSpeedY());
                //ball.setY(ball.getY() + ball.getSpeedY());
                brick.remove(i);
                brickX.remove(i);
                brickY.remove(i);
            }
            else {
                float tempY = ball.getY() + ball.getSizeY() / 2;
                float tempXLeft = ball.getX() + ball.getSizeX();
                if (isCollison(ball.getX(), tempY, brickX.get(i), brickY.get(i), side)
                        || isCollison(tempXLeft, tempY, brickX.get(i), brickY.get(i), side)) {
                    ball.setSpeedX(-ball.getSpeedX());
                    //ball.setX(ball.getX() + ball.getSpeedX());
                    brick.remove(i);
                    brickX.remove(i);
                    brickY.remove(i);
                }
            }
        }
    }

    private boolean isCollison(float ax, float ay, float bx, float by, float bs){
        return bx <= ax && ax <= bx+bs && by <= ay && ay <= by+bs;
    }

}
