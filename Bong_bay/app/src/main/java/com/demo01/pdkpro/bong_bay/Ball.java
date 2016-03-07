package com.demo01.pdkpro.bong_bay;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class Ball extends ObjectFather{
    float SpeedX,SpeedY;
    int val = 1;//hướng trái banh. =1 -> đang chạy về phía bên trên << hướng này là hướng nhìn của người chơi nhìn vào
    boolean cf = true;
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

    public void update(Player player1,Player player2,ArrayList<Boom> ArrBoom){
        //nếu không có gì xảy ra thì
        this.setX(this.getX()+this.getSpeedX());
        this.setY(this.getY()+this.getSpeedY());
        //trường hợp chay ra khỏi màn hình 2 bên trái phải màn hình
        if(this.getX() <0 || this.getX()+this.getSizeX() > this.getView().getWidth()){
            SpeedX = -SpeedX;
            this.setX(this.getX()<0 ? this.getxMin():this.getxMax()-this.getSizeX());
            this.PlaySong();
        }
        //trường hợp vượt quá lên trên hoặc xún dưới của màn hình
        if(this.getY() <0 || this.getY() + this.getSizeY() > this.getView().getHeight()&&cf){
            SpeedY = -SpeedY;
            this.setY(this.getY()<0 ? this.getyMin():this.getyMax()-this.getSizeY());
            //TRUONG HOP CHET ROI
            //cf = false;
            this.PlaySong();
            String note = this.getY() < 0 ? "Máy":"Người";
            //Toast.makeText(this.view.getContext(), note+" Thua rồi nhé !!!!", Toast.LENGTH_SHORT).show();
        }
        //truong hợp va chamj voi các thanh trượt
        if(this.val>0){

        }
        //banh đang bay qua bên trên tức là player 2
        if(checkCollision(player2,-10)){
            xuLyVaCham(player2, true);
        }
        //banh đang bay qua bên trên tức là player 1
        else if(checkCollision(player1, 10)){
            xuLyVaCham(player1,false);
        }
    }

    public void xuLyVaCham(Player player,boolean cfAI){
        player.PlaySong();
        if(cfAI){
            if(collisionBT(player)){
                SpeedY = -SpeedY;
                this.setY(player.getY()+this.getSizeY());
            } else if(collosionLR(player)){
                SpeedX = -SpeedX;
            }
        }else{
            if(collisionBT(player)){
                SpeedY = -SpeedY;
                this.setY(player.getY()+10-this.getSizeY());
            } else if(collosionLR(player)){
                SpeedX = -SpeedX;
            }
        }
    }

    public boolean collisionBT(Player player){
        return (this.getX() + this.getSizeX() >= player.getX() || this.getX() >= player.getX())&& this.getX() <= player.getX()+player.getSizeX();
    }

    public boolean collosionLR(Player player){
        return (this.getY() + this.getSizeY() >= player.getY() || this.getY() >= player.getY())&& this.getY() <= player.getY()+player.getSizeY();
    }

    public boolean checkCollision(Player player,int delta){
        return this.getX()+this.getSizeX() >= player.getX() && this.getX()  <= player.getX()+player.getSizeX() && this.getY()+this.getSizeY()>=player.getY()+delta&& this.getY() <= player.getY()+player.getSizeY()+delta;
    }
    //taoj nhac
    public void setSong(Context context){
        mSoundManager = new SoundManager();
        mSoundManager.initSounds(context);
        mSoundManager.addSound(0,R.raw.chamtuong);

    }
    // phát nhạc
    public void PlaySong(){
        //this.song.start();
        this.mSoundManager.playSound(0);
    }
    //stop Song
    public void StopSong(){
        //this.song.stop();
    }
}