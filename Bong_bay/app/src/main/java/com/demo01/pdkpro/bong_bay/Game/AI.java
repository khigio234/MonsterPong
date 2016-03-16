package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;

/**
 * Created by CO on 3/1/2016.
 */
public class AI extends ObjectFather {
    public AI(int sizeX,int sizeY,int Image, float x, float y, Context context){
        super(sizeX,sizeY,Image,x,y,context);
    }
    public void update(Ball ball, MainGame view){
            // calculate ideal position
            float desty = (float)(ball.getX() - (this.getSizeX() - ball.getSizeX())*0.5);
            // ease the movement towards the ideal position
            this.setX(this.getX() + (float)((desty - this.getX())*0.2));
            // keep the paddle inside of the canvas
            this.setX(Math.max(Math.min(this.getX(),view.getWidth() - this.getSizeX()),0));
    }
}
