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
        canvas.drawBitmap(this.ObjectDraw,this.x,this.y,null);
    }

    public boolean checkCollision(Ball ball){
        //return ball.x+ball.getBallSizeX() >= this.getX() && ball.x  <= this.getX()+this.getBallSizeX() && ball.y+ball.getBallSizeY()>=this.getY()&& ball.y <= this.getY()+this.getBallSizeY();
        if(KhoanCach(ball.getX(),ball.getY(),this.x,this.y)<this.getSizeX()){
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
            //xét vị trí chạm mép dưới + mép trên
            if((ball.getX()+ball.getBallSizeX()>=this.x || ball.getX()>=this.x) && ball.getX()<=this.x+this.getSizeX()) {
                ball.setSpeedY(-ball.getSpeedY());
                ball.setY(ball.getY() + ball.getSpeedY());
            }
            //trường hợp chạm mép 2 bên
            else if((ball.getY()+ball.getBallSizeY()>=this.y||ball.getY()>=this.y) && ball.getY()<=this.y+this.getSizeY()){
                ball.setSpeedX(-ball.getSpeedX());
                ball.setX(ball.getX() + ball.getSpeedX());
            }
        }
    }
}
