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

    public Boom(int sizeX,int sizeY,int Image, int x, int y, Context context)
    {
        super(sizeX,sizeY,Image,x,y,context);
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
//            this.PlaySong();
            //xét vị trí chạm mép dưới + mép trên
            if(isCollision(ball.getX(), ball.getSizeX(), this.getX(), this.getSizeX()))
            {
                ball.setSpeedY(-ball.getSpeedY());
                ball.setY(ball.getY() + ball.getSpeedY());
            }
            //trường hợp chạm mép 2 bên
            else if(isCollision(ball.getY(), ball.getSizeY(), this.getY(), this.getSizeY())){
                ball.setSpeedX(-ball.getSpeedX());
                ball.setX(ball.getX() + ball.getSpeedX());
            }
        }
    }
    public boolean isCollision(float a_coordinate , int a_size, float b_coordinate, int b_size){
        return (a_coordinate + a_size >= b_coordinate || a_coordinate >= b_coordinate && a_coordinate <= a_coordinate + b_size);
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
