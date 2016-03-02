package com.demo01.pdkpro.bong_bay;

/**
 * Created by CO on 1/22/2016.
 */
public class Player extends ObjectFather{
    public Player(int panddleSizeX,int panddleSizeY,int image){
        super(panddleSizeX,panddleSizeY,image);
    }
    public void update(Ball ball){
        if(ball.x > 0 && ball.x <= ball.getxMax()-this.getSizeX())
        {
            this.setX(ball.x);
        }
    }
}
