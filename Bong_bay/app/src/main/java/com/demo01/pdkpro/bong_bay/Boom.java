package com.demo01.pdkpro.bong_bay;

import android.graphics.Canvas;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class Boom extends ObjectFather {
    boolean isLife = true;
    public Boom(int ballSizeX,int ballSizeY,int Image){
        super(ballSizeX,ballSizeY,Image);
    }

    public Boom(float x,float y){
        this.x = x;
        this.y = y;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean isLife){
        this.isLife = isLife;
    }

    public void DrawBall(Canvas canvas){
        if(isLife())
        canvas.drawBitmap(this.BallDraw,this.x,this.y,null);
    }

    public boolean checkCollision(Ball ball){
        //return ball.x+ball.getBallSizeX() >= this.getX() && ball.x  <= this.getX()+this.getBallSizeX() && ball.y+ball.getBallSizeY()>=this.getY()&& ball.y <= this.getY()+this.getBallSizeY();
        if(KhoanCach(ball.getX(),ball.getY(),this.x,this.y)<this.getBallSizeX()){
            return true;
        }
        return false;
    }
    public boolean checkIsLifed(Boom boom){
        return this.x == boom.x && this.y == boom.y;
    }
    private float KhoanCach(float x1,float y1, float x2, float y2){
        float Xa = x2-x1;
        float Ya = y2 - y1;
        return (float)Math.sqrt(Xa*Xa + Ya*Ya);
    }

    public void checkCollisionToBall(Ball ball){
        //kiểm tra va chạm với boom
        if (this.isLife()&& this.checkCollision(ball)){
            this.setLife(false);
            ball.setSpeedY(-ball.getSpeedY());
            this.y = this.y + this.getBallSizeY();
        }
    }
}
