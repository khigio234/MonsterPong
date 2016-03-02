package com.demo01.pdkpro.bong_bay;

import java.util.ArrayList;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class Ball extends ObjectFather{
    float SpeedX,SpeedY;
    int val = 1;//hướng trái banh. =1 -> đang chạy về phía bên trên << hướng này là hướng nhìn của người chơi nhìn vào
    boolean cf = true;
    public Ball(){

    }
    public Ball(int ballSizeX,int ballSizeY,int Image,float SpeedX,float SpeedY){
        this.SizeX = ballSizeX;
        this.SizeY = ballSizeY;
        this.Image = Image;
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

    public void setBallSizeX(int ballSizeX) {
        this.SizeX = ballSizeX;
    }

    public int getBallSizeX() {
        return SizeX;
    }

    public void setBallSizeY(int ballSizeY) {
        this.SizeY = ballSizeY;
    }

    public int getBallSizeY() {
        return SizeY;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setxMax(float xMax) {
        this.xMax = xMax;
    }

    public float getxMax() {
        return xMax;
    }

    public void setxMin(float xMin) {
        this.xMin = xMin;
    }

    public float getxMin() {
        return xMin;
    }
    public void update(Player player1,Player player2,ArrayList<Boom> ArrBoom){
        //nếu không có gì xảy ra thì
        this.x += this.getSpeedX();
        this.y += this.getSpeedY();
        //trường hợp chay ra khỏi màn hình 2 bên trái phải màn hình
        if(this.x<0 || this.x+this.SizeX > this.view.getWidth()){
            SpeedX = -SpeedX;
            this.x = this.x<0 ? this.xMin:this.xMax-SizeY;
        }
        //trường hợp vượt quá lên trên hoặc xún dưới của màn hình
        if(this.y<0||this.y+this.SizeY>this.view.getHeight()&&cf){
            SpeedY = -SpeedY;
            this.y = this.y<0 ? this.yMin:this.yMax-this.SizeY;
            //TRUONG HOP CHET ROI
            //cf = false;
            String note = this.y < 0 ? "Máy":"Người";
            //Toast.makeText(this.view.getContext(), note+" Thua rồi nhé !!!!", Toast.LENGTH_SHORT).show();
        }
        //truong hợp va chamj voi các thanh trượt
        if(this.val>0){//banh đang bay qua bên trên tức là player 2

        }
        if(checkCollision(player2)){
            SpeedY = -SpeedY;
            this.y = player2.getY() + this.getBallSizeY();
        }
        if(checkCollision(player1)){//banh đang bay qua bên trên tức là player 1
            SpeedY = -SpeedY;
            this.y = player1.getY() - this.getBallSizeY();
        }

    }

    public boolean checkCollision(Player player){
        return this.x+SizeX >= player.getX() && this.x  <= player.getX()+player.getSizeX() && this.y+SizeY>=player.getY()&& this.y <= player.getY()+player.getSizeY();
    }

}