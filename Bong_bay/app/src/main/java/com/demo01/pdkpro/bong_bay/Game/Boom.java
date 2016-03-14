package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;
import android.graphics.Canvas;

import com.demo01.pdkpro.bong_bay.R;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class Boom extends ObjectFather {
    int songFile;
    SoundManager mSoundManage;

    public Boom(int ballSizeX,int ballSizeY,int Image){
        super(ballSizeX,ballSizeY,Image);
    }

    public Boom(float x,float y){
        this.setX(x);
        this.setY(y);
    }

    public void DrawBall(Canvas canvas){
        if(isLife())
        canvas.drawBitmap(this.getObjectDraw(),this.getX(),this.getY(),null);
    }

    public boolean checkCollision(Ball ball){
        //return ball.x+ball.getBallSizeX() >= this.getX() && ball.x  <= this.getX()+this.getBallSizeX() && ball.y+ball.getBallSizeY()>=this.getY()&& ball.y <= this.getY()+this.getBallSizeY();
        if(KhoanCach(ball.getX(),ball.getY(),this.getX(),this.getY())<this.getSizeX()){
            return true;
        }
        return false;
    }
    public boolean checkIsLifed(Boom boom){
        return this.getX() == boom.getY() && this.getY() == boom.getY();
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
            this.PlaySong();
            //xét vị trí chạm mép dưới + mép trên
            if((ball.getX()+ball.getSizeX()>=this.getX() || ball.getX()>=this.getX()) && ball.getX()<=this.getX()+this.getSizeX()) {
                ball.setSpeedY(-ball.getSpeedY());
                ball.setY(ball.getY() + ball.getSpeedY());
            }
            //trường hợp chạm mép 2 bên
            else if((ball.getY()+ball.getSizeY()>=this.getY()||ball.getY()>=this.getY()) && ball.getY()<=this.getY()+this.getSizeY()){
                ball.setSpeedX(-ball.getSpeedX());
                ball.setX(ball.getX() + ball.getSpeedX());
            }
        }
    }

    //taoj nhac
    public void setSong(Context context){
        mSoundManage = new SoundManager();
        mSoundManage.initSounds(context);
        mSoundManage.addSound(0, R.raw.chamquai);
    }
    // phát nhạc
    public void PlaySong(){
        this.mSoundManage.playSound(0);
    }
}
