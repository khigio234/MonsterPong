package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;

import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class Ball extends ObjectFather{
    float SpeedX,SpeedY;
    int val = 1;//hướng trái banh. =1 -> đang chạy về phía bên trên << hướng này là hướng nhìn của người chơi nhìn vào
    SoundManager mSoundManager;

    public Ball(){

    }
    public Ball(int ballSizeX,int ballSizeY,int Image,float SpeedX,float SpeedY){
        this.setSizeX(ballSizeX);
        this.setSizeY(ballSizeY);
        this.setImage(Image);
        this.SpeedX = SpeedX;
        this.SpeedY = SpeedY;
    }

    public void setSpeedX(float speedX) {
        this.SpeedX = speedX;
    }

    public float getSpeedX() {
        return SpeedX;
    }

    public void setSpeedY(float speedY) {
        this.SpeedY = speedY;
    }

    public float getSpeedY() {
        return SpeedY;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void update(Player player,AI ai,ArrayList<Boom> ArrBoom){
        //nếu không có gì xảy ra thì
        this.setX(this.getX()+this.getSpeedX());
        this.setY(this.getY()+this.getSpeedY());
        //trường hợp chay ra khỏi màn hình 2 bên trái phải màn hình
        if(this.getX() <0 || this.getX()+this.getSizeX() > this.getView().getWidth()){
            SpeedX = -SpeedX;
            this.setX(this.getX()<0 ? this.getxMin():this.getxMax()-this.getSizeX());
            this.playSong(0);
        }
        //trường hợp vượt quá lên trên hoặc xún dưới của màn hình
        if(this.getY() <0 || this.getY() + this.getSizeY() > this.getView().getHeight()){
            if(this.getY()<0){//win
                this.playSong(2);
            }else{//lose
                this.playSong(1);
            }
            //cho trai banh chet
            this.setLife(false);
        }
        //truong hợp va chamj voi các thanh trượt
        if(this.val>0){

        }
        //banh đang bay qua bên trên tức là player 2
        if(checkCollision(ai,-10)){
            xuLyVaCham(ai, true);
        }
        //banh đang bay qua bên trên tức là player 1
        else if(checkCollision(player, 10)){
            xuLyVaCham(player,false);
        }
    }

    public void xuLyVaCham(ObjectFather obj,boolean cfAI){
        //obj.PlaySong();
        if(cfAI){
            if(collisionBT(obj)){
                SpeedY = -SpeedY;
                this.setY(obj.getY()+this.getSizeY());
            } else if(collosionLR(obj)){
                SpeedX = -SpeedX;
            }
        }else{
            if(collisionBT(obj)){
                SpeedY = -SpeedY;
                this.setY(obj.getY()+10-this.getSizeY());
                //biet vi tri va cham voi ban gat
                if(this.getX()>obj.getX()+obj.getSizeX()/2){
                    //se set cho x tang
                    if(this.getSpeedX()<0){//x hien tai dang giam
                        this.setSpeedX(-this.getSpeedX());
                    }
                }else{
                    //se set cho x giam
                    if(this.getSpeedX()>0){//x hien tai dang tang
                        this.setSpeedX(-this.getSpeedX());
                    }
                }
            } else if(collosionLR(obj)){
                SpeedX = -SpeedX;
            }
        }
    }
    //va cham tren
    public boolean collisionBT(ObjectFather obj){
        return (this.getX() + this.getSizeX() >= obj.getX() || this.getX() >= obj.getX())&& this.getX() <= obj.getX()+obj.getSizeX();
    }
    //va cham trai phai
    public boolean collosionLR(ObjectFather obj){
        return (this.getY() + this.getSizeY() >= obj.getY() || this.getY() >= obj.getY())&& this.getY() <= obj.getY()+obj.getSizeY();
    }
    //kiem tra va cham
    public boolean checkCollision(ObjectFather obj,int delta){
        return this.getX()+this.getSizeX() >= obj.getX() && this.getX()  <= obj.getX()+obj.getSizeX() && this.getY()+this.getSizeY()>=obj.getY()+delta&& this.getY() <= obj.getY()+obj.getSizeY()+delta;
    }
    //taoj nhac
    public void setSong(Context context){
        mSoundManager = new SoundManager();
        mSoundManager.initSounds(context);
        mSoundManager.addSound(0, R.raw.chamtuong);
        mSoundManager.addSound(1, R.raw.lose);
        mSoundManager.addSound(2, R.raw.win);
    }
    // phát nhạc
    public void playSong(int index){
        this.mSoundManager.playSound(index);
    }
    //stop Song
    public void StopSong(){
    }
}