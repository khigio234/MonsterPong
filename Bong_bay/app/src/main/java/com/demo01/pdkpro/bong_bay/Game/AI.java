package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;

/**
 * Created by CO on 3/1/2016.
 */
public class AI extends ObjectFather {
    public AI(int sizeX,int sizeY,int Image, float x, float y, Context context){
        super(sizeX,sizeY,Image,x,y,context);
    }
    public void update(Ball ball){
        if(ball.getX() > 0 && ball.getX() <= ball.getxMax()-this.getSizeX())
        {
            this.setX(ball.getX());
        }
    }
}
