package com.demo01.pdkpro.bong_bay.GamePlay;

import android.content.Context;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class Ball extends Components{
    float velocityX,velocityY;
    int speed =10;
    SoundColissionManage mSoundColissionManage;

    public Ball(){

    }
    public Ball(int sizeX,int sizeY,int Image, float x, float y, Context context,float velocityX,float velocityY){
        super(sizeX,sizeY,Image,x,y,context);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setvelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getvelocityX() {
        return velocityX;
    }

    public void setvelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public float getvelocityY() {
        return velocityY;
    }

    private boolean isCollisonPaddle(float ax, float ay, float aw, float ah, float bx, float by,float bw, float bh){
        return ax < bx+bw && ay < by+bh && bx < ax+aw && by < ay+ah;
    }

    public void update(Player player, Computer computer) {

        // cap nhat vi tri cua ball
        this.setX(this.getX() + velocityX);
        this.setY(this.getY() + velocityY);
        // kiem tra banh co ra ngoai man hinh hay k, neu co thi bat nguoc lai
        if (0 > this.getX() || this.getX() + this.getSizeX() > this.getView().getWidth()) {
            float offset = velocityX < 0 ? 0 - this.getX() : this.getView().getWidth() - (this.getX() + this.getSizeX());
            this.setX(this.getX() + 2 * offset);
            velocityX *= -1;
            playSong(1);
        }
        if (velocityY > 0) {
            processingCollisonPaddle(player, -1f);

        } else {
            processingCollisonPaddle(computer, 1f);
        }
    }

    private void processingCollisonPaddle(Components obj, float flagNavigation){
        if(isCollisonPaddle(obj.getX(), obj.getY(), obj.getSizeX(), obj.getSizeY(),
                this.getX(), this.getY(), this.getSizeX(), this.getSizeY()))
        {
            float temp = obj.getY() + flagNavigation*obj.getSizeY();
            this.setY(temp);
            float n = (this.getX() + this.getSizeX() - obj.getX())/(obj.getSizeX() + this.getSizeX());
            float phi = (float)(0.25*Math.PI*(2*n-1));

            float smash = (float)Math.abs(phi) > (float)(0.2*Math.PI) ? (float)1.5 : 1;
            this.velocityX = (int)(smash*speed*Math.sin(phi));
            this.velocityY = (int)(flagNavigation*smash*speed*Math.cos(phi));
            playSong(0);
        }

    }
}